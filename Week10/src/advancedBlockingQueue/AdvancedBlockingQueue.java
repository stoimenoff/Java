package advancedBlockingQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import measurementTool.Memory;

public class AdvancedBlockingQueue<T> implements Memory<T>{

	private ArrayList<Queue<T>> mQueues;
	private ArrayList<Lock> mLocks;
	private ArrayList<Condition> notFull;
	private ArrayList<Condition> notEmpty;
	private final int mCapacity;
	private final int mParts;
	private AtomicInteger counter;

	private AtomicInteger mPutIndex;
	private AtomicInteger mGetIndex;

	public AdvancedBlockingQueue(int capacity, int queues) {
		if (queues <= 0 || capacity <= 0) {
			throw new IllegalArgumentException();
		}
		mQueues = new ArrayList<Queue<T>>();
		for (int i = 0; i < queues; i++) {
			mQueues.add(new LinkedList<T>());
		}
		mLocks = new ArrayList<Lock>();
		notFull = new ArrayList<Condition>();
		notEmpty = new ArrayList<Condition>();
		for (int i = 0; i < queues; i++) {
			Lock lock = new ReentrantLock(/* true */);
			mLocks.add(lock);
			notFull.add(lock.newCondition());
			notEmpty.add(lock.newCondition());
		}
		mCapacity = capacity;
		mParts = queues;
		counter = new AtomicInteger(0);
		mPutIndex = new AtomicInteger(0);
		mGetIndex = new AtomicInteger(0);
	}

	public void put(T element) throws InterruptedException {
		int myIndex = mPutIndex.getAndIncrement() % mParts;
		mLocks.get(myIndex).lock();
		try {
			while (counter.getAndAdd(0) == mCapacity) {
				notFull.get(myIndex).await();
			}
			mQueues.get(myIndex).add(element);
			counter.getAndIncrement();
			notEmpty.get(myIndex).signal();
		} finally {
			mLocks.get(myIndex).unlock();
		}
	}
	
	public T take() throws InterruptedException {
		int myIndex = mGetIndex.getAndIncrement() % mParts;
		mLocks.get(myIndex).lock();
		T element = null;
		try {
			while (counter.getAndAdd(0) == 0) {
				notEmpty.get(myIndex).await();
			}
			element = mQueues.get(myIndex).poll();
			counter.getAndDecrement();
			notFull.get(myIndex).signal();
		} finally {
			mLocks.get(myIndex).unlock();
		}
		return element;
	}

}
