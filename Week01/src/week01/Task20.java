package week01;

public class Task20 {
	public static void main(String[] args){
		System.out.println(isPalindrome(1569651));
	}
	public static boolean isPalindrome(int argument){
		int argCopy = argument, argReverse = 0;
		while(argCopy > 0){
			argReverse *= 10;
			argReverse += argCopy%10;
			argCopy /= 10;
		}
		return (argument == argReverse);
	}
}
