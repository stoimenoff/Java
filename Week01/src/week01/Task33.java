package week01;

import java.util.ArrayList;

public class Task33 {
	private static ArrayList<char[]> buttons = new ArrayList<char[]>();

	public static String numbersToMessage(int[] numbers) {
		initButtons();
		boolean isUpperCase = false;
		int count = 0;
		char[] button;
		char c;
		StringBuilder message = new StringBuilder();
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] == 1) {
				isUpperCase = !isUpperCase;
				continue;
			}
			if (numbers[i] == -1)
				continue;
			count = 0;
			while (i + 1 < numbers.length && numbers[i] == numbers[i + 1]) {
				count += 1;
				i++;
			}
			button = buttons.get(numbers[i]);
			c = button[count % button.length];
			if (isUpperCase) {
				c = Character.toUpperCase(c);
				isUpperCase = false;
			}
			message.append(c);
		}
		return message.toString();
	}

	public static ArrayList<Integer> messageToNumbers(String message) {
		ArrayList<Integer> sequence = new ArrayList<Integer>();
		ArrayList<Integer> symbol;
		for (int i = 0; i < message.length() - 1; i++) {
			symbol = symbolToSequence(message.charAt(i), message.charAt(i + 1));
			sequence.addAll(symbol);
		}
		symbol = symbolToSequence(message.charAt(message.length() - 1), ' ');
		sequence.addAll(symbol);
		return sequence;
	}

	private static void initButtons() {
		buttons.add(new char[] { ' ' });
		buttons.add(new char[] { '^' });
		buttons.add(new char[] { 'a', 'b', 'c' });
		buttons.add(new char[] { 'd', 'e', 'f' });
		buttons.add(new char[] { 'g', 'h', 'i' });
		buttons.add(new char[] { 'j', 'k', 'l' });
		buttons.add(new char[] { 'm', 'n', 'o' });
		buttons.add(new char[] { 'p', 'q', 'r', 's' });
		buttons.add(new char[] { 't', 'u', 'v' });
		buttons.add(new char[] { 'w', 'z', 'y', 'z' });
	}

	private static ArrayList<Integer> symbolToSequence(char symbol, char nextSymbol) {
		ArrayList<Integer> sequence = new ArrayList<Integer>();
		if (Character.isLetter(symbol)) {
			char letter = symbol;
			if (Character.isUpperCase(letter)) {
				letter = Character.toLowerCase(symbol);
				sequence.add(1);
			}
			int button = button(letter);
			int count = count(letter);
			for (int i = 0; i < count; i++) {
				sequence.add(button);
			}
			if (Character.isLetter(nextSymbol) && button(letter) == button(nextSymbol)) {
				sequence.add(-1);
			}
		}
		if (Character.isWhitespace(symbol)) {
			sequence.add(0);
		}
		return sequence;
	}

	private static int button(char letter) {
		letter = Character.toLowerCase(letter);
		int modifier = 0;
		if ((int) letter >= 115)
			modifier += 1;
		if ((int) letter == 122) {
			modifier += 1;
		}
		return ((int) letter - 97 - modifier) / 3 + 2;
	}
	
	private static int count(char letter) {
		letter = Character.toLowerCase(letter);
		int modifier = 0;
		int count = 0;
		if ((int) letter > 115)
			modifier += 1;
		if ((int) letter == 122 || (int) letter == 115) {
			modifier += 1;
			count += 1;
		}
		count += (((int) letter - 97 - modifier) % 3 + 1);
		return count;
	}
	public static void main(String[] args) {
		System.out
				.println(numbersToMessage(new int[] { 1, 4, 4, 4, 8, 8, 8, 6, 6, 6, 0, 3, 3, 0, 1, 7, 2, 6, 6, 3, 2 }));
		System.out.println(messageToNumbers("Ivo e Panda"));
		System.out.println(messageToNumbers("aabbcc"));

	}
}
