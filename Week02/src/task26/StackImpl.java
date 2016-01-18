package task26;

import task26.Node;
import task26.Stack;

public class StackImpl<T> implements Stack<T> {

	protected Node<T> root;
	protected int size = 0;

	protected StackImpl() {
		root = null;
	}

	public boolean isEmpty() {
		return (this.size == 0);
	}

	public int size() {
		return this.size;
	}

	public void push(T obj) {
		if (root == null) {
			root = new Node<T>(obj);
		} else {
			Node<T> new_root = new Node<T>(obj, root);
			root = new_root;
		}
		size += 1;
	}

	public T pop() {
		if (size > 0) {
			T obj = (T) root.getObject();
			root = root.next();
			size -= 1;
			return obj;
		}
		return null;
	}

	public void empty() {
		root = null;
	}

}
