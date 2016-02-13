package week02;

import java.util.HashMap;

public class Task22 {
	public static void main(String[] args) {
		System.out.println(uniqueWordsCount(new String[] {"apple", "banana", "apple", "pie"}));
		System.out.println(uniqueWordsCount(new String[] {"java", "java", "java", "android"}));
		System.out.println(uniqueWordsCount(new String[] {"HELLO!", "HELLO!", "HELLO!", "HELLO!"}));
	}
	public static int uniqueWordsCount(String[] arr) {
		HashMap<String, Integer> occurences = new HashMap<String, Integer>();
		for (int i = 0; i < arr.length; i++) {
			occurences.put(arr[i], 0);
		}
		return occurences.size();
	}
}
