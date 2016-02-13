package week01;

public class Task09 {
	public static void main(String[] args){
		System.out.println(getLargestPalindrome(678951234));
	}
	public static long getLargestPalindrome(long n){
		long palindrome = 1, nCopy = n, nNumDigits = 0;
		while(nCopy > 0){
			nNumDigits += 1;
			nCopy /= 10;
		}
		if(n == tenToPower(nNumDigits - 1)){
			n -= 1;
			nNumDigits -= 1;
		}
		long largestGuess = n/tenToPower(nNumDigits/2);
		boolean hasPivot = nNumDigits%2 != 0;
		palindrome = palindromize(largestGuess, hasPivot, (nNumDigits+1)/2);
		while(palindrome > n){
			largestGuess -= 1;
			palindrome = palindromize(largestGuess, hasPivot, (nNumDigits+1)/2);
		}
		return palindrome;
	}
	public static long tenToPower(long power){
		long result = 1;
		for(int i  = 0; i < power; i++){
			result *= 10;
		}
		return result;
	}
	public static long palindromize(long n, boolean hasPivot, long digitsNum){
		long digitsToAdd = digitsNum;
		long palindrome = n*tenToPower(digitsToAdd);
		if(hasPivot){
			digitsToAdd -= 1;
			n/=10;
			palindrome /= 10;
		}
		for(int i  = 0; i < digitsToAdd; i++){
			palindrome += (n%10)*tenToPower(digitsToAdd - i - 1);
			n = n/10;
		}
		return palindrome;
	}
}
