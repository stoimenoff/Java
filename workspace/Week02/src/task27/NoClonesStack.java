package task27;

import java.util.HashMap;

import task26.StackImpl;

public class NoClonesStack<T> extends StackImpl<T> {

	private HashMap<T, Boolean> items;

	public NoClonesStack() {
		super();
		items = new HashMap<T, Boolean>();
	}

	private boolean doesNotContain(T obj) {
		return (items.get(obj) != null && items.get(obj) != true);
	}

	public void push(T obj) {
		if (this.doesNotContain(obj)) {
			super.push(obj);
			items.put(obj, true);
		} else {
			System.out.println("Object already in stack !!!");
		}
	}
	
	public T pop() {
		T obj = super.pop();
		items.remove(obj);
		return obj;
	}
	
	public void empty() {
		super.empty();
		items.clear();
	}
}
