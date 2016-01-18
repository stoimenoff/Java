package friday;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import friday.MapEntry;

import java.util.Set;
import java.util.TreeSet;

public class HashMap<K, V> {

	final static int INITIAL_CAPACITY = 16;
	final static float POPULATION_RATIO = 0.75F;
	private int capacity;
	private int size;

	List<LinkedList<MapEntry<K, V>>> buckets;

	HashMap(int capacity) {
		this.capacity = capacity;
		this.size = 0;
		resizeBuckets(capacity);
	}

	HashMap() {
		this(INITIAL_CAPACITY);
	}

	private void resizeBuckets(int size) {
		buckets = new ArrayList<LinkedList<MapEntry<K, V>>>(size);
		for (int i = 0; i < size; i++) {
			buckets.add(new LinkedList<MapEntry<K, V>>());
		}
	}

	private int hash(K key) {
		return Math.abs(key.hashCode()) % capacity;
	}

	public boolean containsKey(K key) {
		if (key == null) {
			// throw exception
			return false;
		}
		int hash = hash(key);
		LinkedList<MapEntry<K, V>> bucket = buckets.get(hash);
		for (MapEntry<K, V> entry : bucket) {
			if (entry.getKey().equals(key)) {
				return true;
			}
		}
		return false;
	}

	public V get(K key) {
		if (key == null) {
			// throw exception
			return null;
		}
		int hash = hash(key);
		LinkedList<MapEntry<K, V>> bucket = buckets.get(hash);
		for (MapEntry<K, V> entry : bucket) {
			if (entry.getKey().equals(key)) {
				return entry.getValue();
			}
		}
		// no such key exception
		return null;
	}

	public V put(K key, V value) {
		if (key == null) {
			// throw exception
		}
		int hash = hash(key);
		LinkedList<MapEntry<K, V>> bucket = buckets.get(hash);
		V oldValue = null;
		for (MapEntry<K, V> entry : bucket) {
			if (entry.getKey().equals(key)) {
				oldValue = entry.getValue();
				entry.setValue(value);
				size += 1;
				break;
			}
		}
		if (oldValue == null) {
			bucket.add(new MapEntry<K, V>(key, value));
			size += 1;
		}
		if ((double) size / capacity >= POPULATION_RATIO) {
			resize();
		}
		return oldValue;
	}

	public V remove(K key) {
		if (key == null) {
			// throw exception
		}
		int hash = hash(key);
		LinkedList<MapEntry<K, V>> bucket = buckets.get(hash);
		int index = 0;
		V oldValue = null;
		for (MapEntry<K, V> entry : bucket) {
			if (entry.getKey().equals(key)) {
				oldValue = entry.getValue();
				break;
			}
			index += 1;
		}
		if (index < bucket.size()) {
			bucket.remove(index);
			size -= 1;
		}
		return oldValue;
	}

	public Set<Map.Entry<K, V>> entrySet() {
		Set<Map.Entry<K, V>> entrySet = new TreeSet<Map.Entry<K, V>>();
		for (LinkedList<MapEntry<K, V>> bucket : buckets) {
			for (MapEntry<K, V> entry : bucket) {
				entrySet.add(entry);
			}
		}
		return entrySet;
	}

	private void resize() {
		Set<Entry<K, V>> entrySet = this.entrySet();
		resizeBuckets(2 * capacity);
		K key;
		V value;
		for (Entry<K, V> entry : entrySet) {
			key = entry.getKey();
			value = entry.getValue();
			this.put(key, value);
		}
	}
}
