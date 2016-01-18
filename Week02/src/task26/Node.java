package task26;

public class Node <T>{
	private T object;
	private Node <T> next;
	Node(T object, Node <T> next) {
		this.object = object;
		this.next = next;
	}
	Node(T object) {
		this.object = object;
		this.next = null;
	}
	public T getObject() {
		return this.object;
	}
	public Node <T> next() {
		return this.next;
	}
}
