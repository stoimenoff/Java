package week01;

public class Task32 {
	public static int pScore(int n) {
		int reversedN = reverse(n);
		if (n == reversedN) {
			return 1;
		}
		
		return 1 + pScore(n + reversedN);
	}
	
	public static int reverse(int n) {
		int reversedN = 0;
		while (Math.abs(n) > 0) {
			reversedN *= 10;
			reversedN += n % 10;
			n /= 10;
		}
		return reversedN;
	}
	
	public static void main(String[] args) {
		System.out.println(pScore(121));
		System.out.println(pScore(48));
		System.out.println(pScore(198));
	}
}
