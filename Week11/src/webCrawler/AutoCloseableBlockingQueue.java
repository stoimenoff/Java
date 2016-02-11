package webCrawler;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AutoCloseableBlockingQueue<T> {

	final ReentrantLock lock;
	final Condition notFull;
	final Condition notEmpty;

	private final LinkedList<T> items;
	private final HashSet<T> duplicates;

	private int counter;
	private final int capacity;
	private final int maxWaitPoll;
	private boolean closed;

	AutoCloseableBlockingQueue(int size, int closingThreads) {
		if (size <= 0) {
			throw new IllegalArgumentException();
		}
		counter = 0;
		capacity = size;
		lock = new ReentrantLock(/* true */);
		notFull = lock.newCondition();
		notEmpty = lock.newCondition();
		items = new LinkedList<T>();
		duplicates = new HashSet<T>();
		maxWaitPoll = closingThreads;
		closed = false;
	}
	
	public void add(T element) throws InterruptedException {
		lock.lock();
		try {
			if (!closed && !duplicates.contains(element)) {
				while (counter == capacity) {
					notFull.await();
				}
				items.addLast(element);
				duplicates.add(element);
				counter++;
				notEmpty.signal();
			}
		} finally {
			lock.unlock();
		}
	}

	public T poll() throws InterruptedException {
		lock.lock();
		try {
			while (counter == 0 && !closed) {
				if (lock.getWaitQueueLength(notEmpty) == maxWaitPoll - 1) {
					shutdown();
					continue;
				}
				notEmpty.await();
			}
			T element = items.removeFirst();
			counter--;
			System.out.println(counter);
			notFull.signal();
			return element;
		} catch (NoSuchElementException e) {
			return null;
		} finally {
			lock.unlock();
		}
	}

	private void shutdown() {
		lock.lock();
		try {
			closed = true;
			notEmpty.signalAll();
		} finally {
			lock.unlock();
		}

	}
}
