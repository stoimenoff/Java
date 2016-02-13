package monday;

public class Backpack<T> {
	private T object;

	Backpack(T object) {
		this.object = object;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public String toString() {
		return "Backpacked: " + object.toString();
	}

	public boolean equals(Backpack<T> other) {
		return this.object.equals(other.getObject());
	}
}
