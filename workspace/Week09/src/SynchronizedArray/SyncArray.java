package SynchronizedArray;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SyncArray {
	Object[] elements;
	ArrayList<ReentrantReadWriteLock> locks;
	private final int size;
	private final int chunk;

	SyncArray(int size, int partitions) {
		if (size <= 0) {
			throw new IllegalArgumentException();
		}
		this.size = size;
		elements = new Object[this.size];
		locks = new ArrayList<ReentrantReadWriteLock>(partitions);
		for (int i = 0; i < partitions; i++) {
			locks.add(new ReentrantReadWriteLock());
		}
		chunk = size / partitions;
	}

	public Object get(int index) {
		if (index < 0 && index >= size) {
			throw new IllegalArgumentException();
		}
		Object element = null;
		int bucket = index / chunk;
		locks.get(bucket).readLock().lock();
		try {
			element = elements[index];
		} finally {
			locks.get(bucket).readLock().unlock();
		}
		return element;
	}

	public void set(int index, Object element) {
		if (index < 0 && index >= size) {
			throw new IllegalArgumentException();
		}
		int bucket = index / chunk;
		locks.get(bucket).writeLock().lock();
		try {
			elements[index] = element;
		} finally {
			locks.get(bucket).writeLock().unlock();
		}
	}

	public int size() {
		return size;
	}

}
