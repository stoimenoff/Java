package wednesday;

import java.util.HashMap;
import java.util.Map.Entry;

public class HashMapCoolToString {
	public static String coolToString (HashMap<? extends Object, ? extends Object> hashMap) {
		StringBuilder result = new StringBuilder("HashMap: \n");
		for (Entry<?, ?> entry : hashMap.entrySet()) {
			result.append("KEY : ");
			result.append(entry.getKey().toString());
			result.append(" VALUE : ");
			result.append(entry.getValue().toString());
			result.append("\n");
		}
		return result.toString();
	}
	public static void main(String[] args) {
		HashMap<String, Integer> test= new HashMap<String, Integer>();
		test.put("Pesho nomer edno", 1);
		test.put("Gosho nomer edno", 2);
		test.put("Stamat nomer edno", 5);
		test.put("Gosho nomer dve", 123);
		test.put("Stamat nomer dve", 8);
		test.put("Stamat nomer tri", 534);
		test.put("Pesho nomer dve", 5123);
		System.out.println(coolToString(test));
	}
}