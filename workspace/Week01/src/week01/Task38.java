package week01;

public class Task38 {
	public static int zeroInsert(int n) {
		int modified = 0;
		int digit;
		int prevDigit;
		int base = 1;
		while(Math.abs(n) > 9) {
			digit = n % 10;
			modified += base * digit;
			base *= 10;
			n /= 10;
			prevDigit = n % 10;
			if(prevDigit == digit || (digit + prevDigit)%10 == 0) {
				base *= 10;
			}
		}
		modified += base * n;
		return modified;
	}
	public static void main(String[] args) {
		System.out.println(zeroInsert(116457));
		System.out.println(zeroInsert(55555));
		System.out.println(zeroInsert(1));
		System.out.println(zeroInsert(6446));
	}
}
