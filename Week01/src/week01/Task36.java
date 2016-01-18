package week01;

public class Task36 {
	public static boolean isAnBnWord(String word) {
		if (word == null || word.length() % 2 == 1) {
			return false;
		}
		int half = word.length() / 2;
		for (int i = 0; i < half; i++) {
			if(word.charAt(i) != 'a' || word.charAt(i + half) != 'b') {
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		System.out.println(isAnBnWord(""));
		System.out.println(isAnBnWord("hack"));
		System.out.println(isAnBnWord("aaabb"));
		System.out.println(isAnBnWord("aaabbb"));
		System.out.println(isAnBnWord("aabbaabb"));
		System.out.println(isAnBnWord("bbbaaa"));
		System.out.println(isAnBnWord("aaaaabbbbb"));
	}
}
