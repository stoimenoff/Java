package wednesday;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public class SetIntersection {
	public static void main(String[] args) {
		TreeSet<String> set1 = new TreeSet<String>();
		set1.add("Str1");
		set1.add("Str2");
		set1.add("Str3");
		set1.add("Str4");
		set1.add("Str5");
		TreeSet<String> set2 = new TreeSet<String>();
		set2.add("Str4");
		set2.add("Str5");
		set2.add("Str6");
		TreeSet<String> set3 = new TreeSet<String>();
		set3.add("Str5");
		set3.add("Str6");
		set3.add("Str7");
		set3.add("Str8");
		ArrayList<TreeSet<String>> sets = new ArrayList<TreeSet<String>>();
		sets.add(set1);
		sets.add(set2);
		sets.add(set3);
		TreeSet<String> intersection = intersect(sets);
		System.out.println(intersection);
	}

	public static <T> TreeSet<T> intersect(ArrayList<TreeSet<T>> sets) {
		TreeSet<T> intersection = new TreeSet<T>();
		HashMap<T, Integer> intersector = new HashMap<T, Integer>();
		int size = sets.size();
		int count = 0;
		for (Set<T> set : sets) {
			for (T item : set) {
				count = 1;
				if (intersector.containsKey(item)) {
					count = intersector.get(item) + 1;
				}
				intersector.put(item, count);
			}
		}
		Set<Entry<T, Integer>> entrySet = intersector.entrySet();
		for (Entry<T, Integer> entry : entrySet) {
			if (entry.getValue().equals(size)) {
				intersection.add(entry.getKey());
			}
		}
		return intersection;
	}
}
