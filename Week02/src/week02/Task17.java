package week02;

public class Task17 {
	public static void main(String[] args) {
		System.out.println(fibNumber(10));
	}
	public static int digitsNum(int n) {
		int digitsNum = 0;
		while(n > 0) {
			digitsNum += 1;
			n /= 10;
		}
		return digitsNum;
	}
	public static long fibNumber(int n) {
		if (n < 1)
		{
			return -1;
		}
		long fibNumber = 1;
		int kthFib = 1;
		int kplus1Fib = 1;
		for (int i = 1; i < n; i++) {
			fibNumber *= Math.pow(10, digitsNum(kplus1Fib));
			fibNumber += kplus1Fib;
			kplus1Fib += kthFib;
			kthFib = kplus1Fib - kthFib; 
		}
		return fibNumber;
	}
}
