package wednesday;

import java.util.HashMap;

public class CountWords {
	public static HashMap<String, Integer> countWords(String text) {
		int start = 0;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String word;
		int count = 0;
		for (int i = 0; i < text.length(); i++) {
			if (Character.isAlphabetic(text.charAt(i))) {
				start = i;
				while (Character.isAlphabetic(text.charAt(i))) {
					i++;
				}
				word = text.substring(start, i);
				count = 1;
				if (map.containsKey(word)) {
					count = map.get(word) + 1;
				}
				map.put(word, count);
				i--;
			}
		}
		return map;
	}
	public static void main(String[] args) {
		String text = "Ninjas are all over the place! We are all going to die!";
		HashMap<String, Integer> test = countWords(text);
		System.out.println(test);
	}
}
