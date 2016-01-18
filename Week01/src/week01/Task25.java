package week01;

public class Task25 {
	public static void main(String[] args) {
		String s = "abc123dd34";
		System.out.println(sumOfNumbers(s));
	}
	public static int sumOfNumbers(String input){
		int sum = 0, number = 0;
		for (int i = 0; i < input.length(); i++) {
			number = 0;
			while(i < input.length() && Character.isDigit(input.charAt(i))){
				number *= 10; 
				number += (input.charAt(i) - '0');
				i += 1;
			}
			sum += number;
		}
		return sum;
	}
}
