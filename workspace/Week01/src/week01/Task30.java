package week01;

public class Task30 {
	public static int countVolwes(String word) {
		int count = 0;
		for (int i = 0; i < word.length(); i++) {
			if(isVowel(word.charAt(i))) {
				count += 1;
			}
		}
		return count;
	}

	public static boolean isVowel(char c) {
		if (Character.isLetter(c)) {
			c = Character.toLowerCase(c);
			if (c == 'a' || c == 'i' || c == 'o' || c == 'y' || c == 'u' || c == 'e') {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(countVolwes("Java"));
		System.out.println(countVolwes("Theistareykjarbunga"));
		System.out.println(countVolwes("grrrrgh"));
		System.out.println(countVolwes("Github is the second best thing that happend to programmers, after the keyboard"));
		System.out.println(countVolwes("A nice day to code!"));
	}
}
