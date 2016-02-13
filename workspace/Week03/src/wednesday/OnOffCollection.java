package wednesday;

import java.util.HashMap;

@SuppressWarnings("serial")
public class OnOffCollection<K, V> extends HashMap<K, V> {

	@Override
	public V put(K key, V value) {
		V old = super.put(key, value);
		if (old.equals(value)) {
			this.remove(key);
		}
		return old;
	}
}
