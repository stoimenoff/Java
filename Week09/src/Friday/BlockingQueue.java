package Friday;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue<T> {

	final Lock lock;
	final Condition notFull;
	final Condition notEmpty;

	private final LinkedList<T> items;

	private int counter;
	private final int capacity;

	BlockingQueue(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException();
		}
		counter = 0;
		capacity = size;
		lock = new ReentrantLock(/* true */); // make it true and lose
												// performance
		notFull = lock.newCondition();
		notEmpty = lock.newCondition();
		items = new LinkedList<T>();
	}

	public void add(T element) throws InterruptedException {
		lock.lock();
		try {
			while (counter == capacity) {
				notFull.await();
			}
			items.addLast(element);
			counter++;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	public T poll() throws InterruptedException {
		lock.lock();
		try {
			while (counter == 0) {
				notEmpty.await();
			}
			T element = items.removeFirst();
			counter--;
			notFull.signal();
			return element;
		} finally {
			lock.unlock();
		}
	}

	public int size() {
		return items.size();
	}

}
