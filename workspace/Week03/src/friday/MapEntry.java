package friday;

import java.util.Map;

final class MapEntry<K, V> implements Map.Entry<K, V>{

	private final K key;
	private V value;

	MapEntry(K firstObj, V secondObj) {
		key = firstObj;
		value = secondObj;
	}

	public final K getKey() {
		return this.key;
	}

	public final V getValue() {
		return this.value;
	}

	public boolean equals(MapEntry<K, V> other) {
		return key.equals(other.getKey()) && value.equals(other.getValue());
	}

	public String toString() {
		return "FIRST: " + key.toString() + " SECOND: " + value.toString();
	}

	@Override
	public V setValue(V value) {
		V old = this.value;
		this.value = value;
		return old;
	}
}
