package week02;

public class Task20 {
	public static void main(String[] args) {
		int a[] = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 5};
		System.out.println(maxEqualConsecutive(a));
	}
	public static int maxEqualConsecutive(int[] items) {
		if (items.length == 0) {
			return 0;
		}
		int longestSeq = 0;
		int tempSeq = 1;
		for (int i = 0; i < items.length - 1; i++) {
			if (items[i] == items[i + 1]) {
				tempSeq += 1;
			} else {
				if (tempSeq > longestSeq) {
					longestSeq = tempSeq;
				}
				tempSeq = 1;
			}
		}
		if (tempSeq > longestSeq) {
			longestSeq = tempSeq;
		}
		return longestSeq;
	}
}
