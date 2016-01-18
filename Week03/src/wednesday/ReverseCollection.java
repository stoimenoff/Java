package wednesday;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ReverseCollection {

	static <T> void reverse(Collection<T> collection) {
		ArrayList<T> reverse = new ArrayList<T>();
		for (T element : collection) {
			reverse.add(0, element);
		}
		Iterator<T> item = collection.iterator();
		while (item.hasNext()) {
			item.next();
			item.remove();
		}
		//collection.clear();
		for (T element : reverse) {
			collection.add(element);
		}
	}

	public static void main(String[] args) {
		Collection<String> col = new ArrayList<String>();
		col.add("A1");
		col.add("A2");
		col.add("A3");
		col.add("A4");
		col.add("A5");
		col.add("A6");
		col.add("A7");
		System.out.println(col);
		reverse(col);
		System.out.println(col);
	}
}
