package secondWebCrawler;

import java.util.Map.Entry;

public class Pair<K, V> implements Entry<K, V> {

	private final K key;
	private V value;

	public Pair(K k, V v) {
		key = k;
		value = v;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public V setValue(V value) {
		V res = value;
		this.value = value;
		return res;
	}

	@Override
	public String toString() {
		return "Key: " + key.toString() + " Value: " + value.toString();
	}

}
