package Wednesday;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Memory<T> {

	final Lock lock;
	final Condition notFull;
	final Condition notEmpty;

	final Object[] items;

	int mToPut, mToTake, counter;

	Memory(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException();
		}
		counter = 0;
		mToPut = 0;
		mToTake = 0;
		lock = new ReentrantLock(/* true */); // make it true and lose
												// performance
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
			if (++mToPut == items.length)
				mToPut = 0;
			counter++;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	public Object take() throws InterruptedException {
		lock.lock();
		try {
			while (counter == 0) {
				notEmpty.await();
			}
			Object element = items[mToTake];
			if (++mToTake == items.length)
				mToTake = 0;
			counter--;
			notFull.signal();
			return element;
		} finally {
			lock.unlock();
		}
	}

	public int size() {
		return items.length;
	}

}
