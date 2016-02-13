package calculator;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Calculator {

	private static Queue<String> reversePolishNotation = new LinkedList<String>();
	private static byte[] priority = { 2, 2, 3, 3, 4, 5 };

	private static byte getByteIndex(String operator) {
		switch (operator) {
		case "+":
			return 0;
		case "-":
			return 1;
		case "*":
			return 2;
		case "/":
			return 3;
		case "^":
			return 4;
		case "!":
			return 5;
		default:
			return -1;
		}
	}

	private static int getPriority(String operator) {
		return priority[getByteIndex(operator)];
	}

	private static boolean isOperator(String operator) {
		return (getByteIndex(operator) != -1);
	}

	private static void getReversePolishNotation(String input) {
		Stack<String> operators = new Stack<String>();
		boolean negativeNumber = false;
		boolean expectOperator = false;
		StringBuilder number = new StringBuilder();
		String operator;
		for (int i = 0; i < input.length(); i++) {
			if (Character.isDigit(input.charAt(i))) {
				while (i < input.length() && (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.')) {
					number.append(input.charAt(i));
					i++;
				}
				i--;
				if (negativeNumber) {
					number.insert(0, '-');
					negativeNumber = false;
				}
				reversePolishNotation.add(number.toString());
				number.setLength(0);
				expectOperator = true;
			} else {
				operator = input.substring(i, i + 1);
				if (isOperator(operator)) {
					if (!expectOperator && operator.equals("-")) {
						negativeNumber = true;
						continue;
					}
					while (operators.size() != 0 && !operators.peek().equals("(")
							&& (getPriority(operators.peek()) > getPriority(operator)
									|| (getPriority(operators.peek()) == getPriority(operator)
											&& !operators.peek().equals(operator)))) {
						reversePolishNotation.add(operators.pop());
					}
					operators.push(operator);
					expectOperator = false;
				} else {
					if (operator.equals("("))
						operators.push(operator);
					if (operator.equals(")")) {
						while (!operators.peek().equals("(")) {
							reversePolishNotation.add(operators.pop());
						}
						operators.pop();
					}
				}
			}
		}
		while (operators.size() > 0) {
			reversePolishNotation.add(operators.pop());
		}
	}

	private static String performOperation(String operator, String num1, String num2) {
		double result = 0;
		if (operator.equals("+")) {
			result = sum(Double.parseDouble(num1), Double.parseDouble(num2));
			return String.valueOf(result);
		}
		if (operator.equals("-")) {
			result = substract(Double.parseDouble(num1), Double.parseDouble(num2));
			return String.valueOf(result);
		}
		if (operator.equals("*")) {
			result = multiply(Double.parseDouble(num1), Double.parseDouble(num2));
			return String.valueOf(result);
		}
		if (operator.equals("/")) {
			result = divide(Double.parseDouble(num1), Double.parseDouble(num2));
			return String.valueOf(result);
		}
		if (operator.equals("^")) {
			result = power(Double.parseDouble(num1), Double.parseDouble(num2));
			return String.valueOf(result);
		}
		// exception - illegal operator
		return String.valueOf(result);
	}

	private static void evaluateReversePolishNotation() {
		Stack<String> numbers = new Stack<String>();
		String token;
		String number1;
		String number2;
		//BigDecimal result = new BigDecimal(0);
		while (reversePolishNotation.size() > 0) {
			token = reversePolishNotation.poll();
			if (isOperator(token)) {
				if (!token.equals("!")) {
					number1 = numbers.pop();
					number2 = numbers.pop();
					numbers.push(performOperation(token, number2, number1));
				} else {
					number1 = numbers.pop();
					BigDecimal fac = factorial(Double.valueOf(number1));
					numbers.push(fac.toString());
				}
			} else {
				numbers.push(token);
			}
		}
		System.out.println(numbers.pop());
	}

	private static <T extends Number> double sum(T num1, T num2) {
		return num1.doubleValue() + num2.doubleValue();
	}

	private static <T extends Number> double substract(T num1, T num2) {
		return num1.doubleValue() - num2.doubleValue();
	}

	private static <T extends Number> double multiply(T num1, T num2) {
		return num1.doubleValue() * num2.doubleValue();
	}

	private static <T extends Number> double divide(T num1, T num2) {
		return num1.doubleValue() / num2.doubleValue();
	}

	private static <T extends Number> double power(T num, T power) {
		return Math.pow(num.doubleValue(), power.doubleValue());
	}

	private static <T extends Number> BigDecimal factorial(T num) {
		BigDecimal factorial = new BigDecimal(1);
		double n = num.doubleValue();
		while (n > 1) {
			factorial = factorial.multiply(BigDecimal.valueOf(n));
			n -= 1;
		}
		return factorial;
	}

	public static void main(String[] args) {
		getReversePolishNotation("2 + (3!)^2 + ((-5  -7 + 9 - 6 )* (-1) )^(1/2) + 2^3^2");
		evaluateReversePolishNotation();
	}
}
