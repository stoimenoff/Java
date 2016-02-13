package SynchronizedArray;

public class NotPartArray {
	Object[] elements;
	private final int size;

	NotPartArray(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException();
		}
		this.size = size;
		elements = new Object[this.size];
	}

	public synchronized Object get(int index) {
		Object element = null;
		element = elements[index];
		return element;
	}

	public synchronized void set(int index, Object element) {
		if (index < 0 && index >= size) {
			throw new IllegalArgumentException();
		}
		elements[index] = element;
	}

	public int size() {
		return size;
	}

}
