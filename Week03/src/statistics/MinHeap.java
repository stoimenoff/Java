package statistics;

import java.util.ArrayList;
import java.util.Collections;

public class MinHeap<T extends Comparable<T>> {
	private ArrayList<T> items;

	MinHeap() {
		items = new ArrayList<T>();
	}

	public int size() {
		return items.size();
	}

	public void add(T item) {
		items.add(item);
		bubbleUpLastElement();
	}

	public T top() {
		if (items.size() > 0) {
			return items.get(0);
		} else {
			// throw exception
			return null;
		}
	}

	public T pop() {
		if (items.size() > 0) {
			T top = items.get(0);
			T lastElement = items.get(items.size() - 1);
			items.remove(items.size() - 1);
			items.set(0, lastElement);
			bubbleDownRoot();
			return top;
		} else {
			// throw exception
			return null;
		}
	}

	private void bubbleUpLastElement() {
		int i = items.size() - 1;
		T item = items.get(i);
		T parent = items.get((i - 1) / 2);
		while (i != 0 && parent.compareTo(item) == 1) {
			Collections.swap(items, i, (i - 1) / 2);
			i = (i - 1) / 2;
			item = items.get(i);
			parent = items.get((i - 1) / 2);
		}
	}

	private int getSmallestChildIndex(int index) {
		if (index * 2 + 1 >= items.size()) { // no children
			return 0;
		}
		if (index * 2 + 2 >= items.size()) { // one child
			return index * 2 + 1;
		}
		T leftChild = items.get(index * 2 + 1);
		T rightChild = items.get(index * 2 + 2);
		if (leftChild.compareTo(rightChild) == -1) {
			return index * 2 + 1;
		} else {
			return index * 2 + 2;
		}
	}

	private void bubbleDownRoot() {
		int i = 0;
		int smallestChildIndex = getSmallestChildIndex(i);
		while (smallestChildIndex != 0) {
			if (items.get(i).compareTo(items.get(smallestChildIndex)) == 1) {
				Collections.swap(items, i, smallestChildIndex);
			} else {
				break;
			}
			i = smallestChildIndex;
			smallestChildIndex = getSmallestChildIndex(i);
		}
	}
}
