package week01;

public class Task17 {
	public static void main(String[] args){
		System.out.println(reverseMe("am I a palindrome ???"));
	}
	public static String reverseMe(String argument){
		char[] reverse = new char[argument.length()];
		for(int i = 0; i < argument.length(); i++){
			reverse[i] = argument.charAt(argument.length() - i - 1);
		}
		return new String(reverse);
	}
}
