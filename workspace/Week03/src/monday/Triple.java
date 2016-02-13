package monday;

public class Triple<T> {
	private T left;
	private T middle;
	private T right;

	public T getLeft() {
		return left;
	}

	public T getMiddle() {
		return middle;
	}

	public T getRight() {
		return right;
	}

	public void setLeft(T object) {
		this.left = object;
	}

	public void setMiddle(T object) {
		this.middle = object;
	}

	public void setRight(T object) {
		this.right = object;
	}

	public String toString() {
		return "Triple:\nLeft: " + left.toString() + "\nMiddle: " + middle.toString() + "\nRight: " + right.toString();
	}

	public boolean equals(Triple<T> other) {
		return left.equals(other.getLeft()) && right.equals(other.getRight()) && middle.equals(other.getMiddle());
	}
}
