package week01;

public class Task11 {
	public static void main(String[] args) {
		System.out.println(pow(2, 31));
	}
	public static long pow(int a, int b) {
		long aB = 1;
		if (b == 0) {
			return 1;
		}
		if (b == 1) {
			return a;
		}
		aB = pow(a, b / 2);
		aB *= aB;
		if (b % 2 == 1) {
			aB *= a;
		}
		return aB;
	}
}
