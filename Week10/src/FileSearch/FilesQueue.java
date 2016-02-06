package FileSearch;

import java.nio.file.Path;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FilesQueue {
	final Lock lock;
	final Condition notFull;
	final Condition notEmpty;

	private final LinkedList<Path> items;

	private AtomicInteger counter;
	private final int capacity;
	private AtomicInteger allFilesQueued;

	FilesQueue(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException();
		}
		counter = new AtomicInteger(0);
		capacity = size;
		lock = new ReentrantLock(/* true */); // make it true and lose
												// performance
		notFull = lock.newCondition();
		notEmpty = lock.newCondition();
		items = new LinkedList<Path>();
		allFilesQueued = new AtomicInteger(0);
	}

	public void add(Path element) throws InterruptedException {
		lock.lock();
		try {
			while (counter.getAndAdd(0) == capacity) {
				notFull.await();
			}
			items.addLast(element);
			counter.getAndIncrement();
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	public Path poll() throws InterruptedException {
		lock.lock();
		try {
			while (counter.getAndAdd(0) == 0 && allFilesQueued.getAndAdd(0) == 0) {
				notEmpty.await();
			}
			Path element = null;
			if (counter.getAndAdd(0) > 0 || allFilesQueued.getAndAdd(0) == 0) {
				element = items.removeFirst();
				counter.getAndDecrement();
				notFull.signal();
			}
			return element;
		} finally {
			lock.unlock();
		}
	}

	public void allFilesQueued() {
		lock.lock();
		try {
			allFilesQueued.getAndSet(1);
			notEmpty.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public boolean wontBeEmptyForever() {
		if (counter.getAndAdd(0) > 0) {
			return true;
		}
		if (allFilesQueued.getAndAdd(0) == 0) {
			return true;
		}
		return false;
	}

	public int size() {
		return items.size();
	}
}
