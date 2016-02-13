package wednesday;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class FirstUnique {
	public static <T> T getFirstUnique(Collection<T> items) {
		HashMap<T, Integer> map = new LinkedHashMap<T, Integer>();
		int count = 0;
		for (T item : items) {
			count = 1;
			if (map.containsKey(item)) {
				count = map.get(item) + 1;
			}
			map.put(item, count);
		}
		for (Entry<T, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 1) {
				return entry.getKey();
			}
		}
		return null;
	}
	public static void main(String[] args) {
		Collection<Integer> ints = Arrays.asList(1,2,3,4,5,5,4,3,1);
		System.out.println(getFirstUnique(ints));
	}
}
