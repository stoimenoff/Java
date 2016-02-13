package advancedBlockingQueue;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import measurementTool.ArrayBlockingQueue;
import measurementTool.Memory;

public class AdvancedBlockingQueue<T> implements Memory<T>{

	private ArrayList<ArrayBlockingQueue<T>> mQueues;
	private final int mCapacity;
	private final int mParts;
	
	private AtomicInteger mPutIndex;
	private AtomicInteger mGetIndex;

	public AdvancedBlockingQueue(int capacity, int queues) {
		if (queues <= 0 || capacity <= 0) {
			throw new IllegalArgumentException();
		}
		if (capacity < queues) {
			queues = capacity;
		}
		mQueues = new ArrayList<ArrayBlockingQueue<T>>();
		int qCapacity = capacity/queues;
		for (int i = 0; i < queues - 1; i++) {
			mQueues.add(new ArrayBlockingQueue<T>(qCapacity));
		}
		//add the last one with bigger capacity
		mQueues.add(new ArrayBlockingQueue<T>(capacity - (queues - 1)*qCapacity));
		mParts = queues;
		mCapacity = capacity;
		mPutIndex = new AtomicInteger(0);
		mGetIndex = new AtomicInteger(0);
	}

	public void put(T element) throws InterruptedException {
		int myIndex = mPutIndex.getAndIncrement() % mParts;
		mQueues.get(myIndex).put(element);
	}
	
	public T take() throws InterruptedException {
		int myIndex = mGetIndex.getAndIncrement() % mParts;
		T element = mQueues.get(myIndex).take();
		return element;
	}
	
	public int getCapacity() {
		return mCapacity;
	}
	
	public int getPartitions() {
		return mParts;
	}
	
}
