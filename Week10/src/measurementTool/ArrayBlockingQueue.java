package measurementTool;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayBlockingQueue<T> implements Memory<T>{

	private final Lock lock;
	private final Condition notFull;
	private final Condition notEmpty;

	private final Object[] items;

	private int mToPut, mToTake, counter;

	ArrayBlockingQueue(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException();
		}
		counter = 0;
		mToPut = 0;
		mToTake = 0;
		lock = new ReentrantLock(/* true */); // make it true and lose
												// performance. Fairness is bad
		notFull = lock.newCondition();
		notEmpty = lock.newCondition();
		items = new Object[size];
	}

	public void put(Object element) throws InterruptedException {
		lock.lock();
		try {
			while (counter == items.length) {
				notFull.await();
			}
			items[mToPut] = element;
			if (++mToPut == items.length) {
				mToPut = 0;
			}
			counter++;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	@SuppressWarnings("unchecked")
	public T take() throws InterruptedException {
		lock.lock();
		try {
			while (counter == 0) {
				notEmpty.await();
			}
			Object element = items[mToTake];
			if (++mToTake == items.length) {
				mToTake = 0;
			}
			counter--;
			notFull.signal();
			return (T) element;
		} finally {
			lock.unlock();
		}
	}

	public int size() {
		return items.length;
	}

}
