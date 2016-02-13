package week01;

public class Task29 {
	public static void main(String[] args) {
		System.out.println(nextHack(8131));
		System.out.println(isHack(8191));
	}

	public static boolean isHack(int n) {
		int numberOfBits = getNumberOfBits(n);
		if (isEven(numberOfBits)) {
			return false;
		} else {
			int hackHalf = n >> ((numberOfBits + 1) >> 1);
			int hackNumber = constructHackNumberFromHalf(hackHalf);
			return (hackNumber == n);
		}
	}

	public static int nextHack(int n) {
		if (n == Integer.MAX_VALUE || n <= 0) {
			return 1;
		}
		int numberOfBits = getNumberOfBits(n);
		int hackNumber = n;
		if (isEven(numberOfBits)) {
			hackNumber = constructSmallestHackNumberWithBitsNumberOf(numberOfBits + 1);
		} else {
			int hackHalf = n >> ((numberOfBits + 1) >> 1);
			hackNumber = constructHackNumberFromHalf(hackHalf);
			while (hackNumber <= n) {
				hackHalf += 1;
				hackNumber = constructHackNumberFromHalf(hackHalf);
			}
		}
		return hackNumber;
	}

	public static int getNumberOfBits(int n) {
		int bitNum = 0;
		while (n > 0) {
			n >>= 1;
			bitNum += 1;
		}
		return bitNum;
	}

	public static int constructHackNumberFromHalf(int half) {
		int bitNum = getNumberOfBits(half);
		int hackNumber = 0;
		hackNumber = half;
		hackNumber <<= 1;
		hackNumber += 1;
		hackNumber <<= bitNum;
		for (int i = bitNum - 1; i >= 0; i--) {
			hackNumber += ((half & 1) << i);
			half >>= 1;
		}
		return hackNumber;
	}

	public static int constructSmallestHackNumberWithBitsNumberOf(int bits) {
		int hackNumber = 1;
		hackNumber <<= bits >> 1;
		hackNumber += 1;
		hackNumber <<= bits >> 1;
		hackNumber += 1;
		return hackNumber;
	}

	public static boolean isEven(int number) {
		return (number & 1) == 0;
	}
}