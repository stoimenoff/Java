package wednesday;

import java.util.ArrayList;
import java.util.Collection;

public class RotateCollection {

	public static <T> void rotate(Collection<T> collection, int rotateStep) {
		ArrayList<T> collectionRotator = new ArrayList<T>();
		for (T item : collection) {
			collectionRotator.add(item);
		}
		collection.clear();
		int size = collectionRotator.size();
		rotateStep %= size;
		if (rotateStep < 0) {
			rotateStep += size;
		}
		T item;
		for (int i = rotateStep; i < size + rotateStep; i++) {
			item = collectionRotator.get(i % size);
			collection.add(item);
		}
	}
	public static void main(String[] args) {
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("Str1");
		arr.add("Str2");
		arr.add("Str3");
		arr.add("Str4");
		arr.add("Str5");
		rotate(arr, -6);
		System.out.println(arr);
	}
}
