package week01;

public class Task22 {
	public static void main(String[] args) {
		String s = "taz*zad";
		System.out.println(getPalindromeLength(s));
	}

	public static int getPalindromeLength(String input) {
		int starIndex = -1, len = 0;
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '*') {
				if (starIndex == -1) {
					starIndex = i;
				} else {
					return -1; // two stars exception
				}
			}
		}
		while (starIndex - len - 1 >= 0 && starIndex + len + 1 < input.length()) {
			if (input.charAt(starIndex - len - 1) != input.charAt(starIndex + len + 1)) {
				break;
			}
			len += 1;
		}
		return len;
	}
}
