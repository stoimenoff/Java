package task25;

final class Pair<T> {

	private final T first;
	private final T second;

	Pair(T firstObj, T secondObj) {
		first = firstObj;
		second = secondObj;
	}

	public final T getFirst() {
		return this.first;
	}

	public final T getSecond() {
		return this.second;
	}

	public boolean equals(Pair<T> other) {
		return first.equals(other.getFirst()) && second.equals(other.getSecond());
	}

	public String toString() {
		return "FIRST: " + first.toString() + " SECOND: " + second.toString();
	}
}
