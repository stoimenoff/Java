package week01;

public class Task31 {
	public static int countConsonants(String word) {
		int count = 0;
		for (int i = 0; i < word.length(); i++) {
			if(isConsonant(word.charAt(i))) {
				count += 1;
			}
		}
		return count;
	}

	public static boolean isConsonant(char c) {
		if (Character.isLetter(c)) {
			c = Character.toLowerCase(c);
			if (c == 'a' || c == 'i' || c == 'o' || c == 'y' || c == 'u' || c == 'e') {
				return false;
			}
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(countConsonants("Java"));
		System.out.println(countConsonants("Theistareykjarbunga"));
		System.out.println(countConsonants("grrrrgh"));
		System.out.println(countConsonants("Github is the second best thing that happend to programmers, after the keyboard"));
		System.out.println(countConsonants("A nice day to code!"));
	}
}
