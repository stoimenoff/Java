package week01;

public class Task19 {
	public static void main(String[] args){
		System.out.println(isPalindrome("pali rtyytr ilap"));
	}
	public static boolean isPalindrome(String argument){
		for(int i = 0; i < argument.length() / 2; i++){
			if(argument.charAt(i) != argument.charAt(argument.length() - i - 1)){
				return false;
			}
		}
		return true;
	}
}
