package wednesday;

import java.util.Stack;

public class CheckBrackets {
	public static boolean checkbrackets(String input) {
		if (input == null) {
			return false;//or throw Exception
		}
		Stack<Character> brackets = new Stack<Character>();
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == ')') {
				if (brackets.isEmpty()) {
					return false;
				}
				brackets.pop();
			} else {
				if (input.charAt(i) == '(') {
					brackets.push('(');
				}
			}
		}
		return true;
	}
	public static void main(String[] args) {
		System.out.println(checkbrackets("()()())))((())("));
		System.out.println(checkbrackets("()()())(()()()"));				//																																																																																																																																																									kyr
	}
}

