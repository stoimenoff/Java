package week01;

public class InterviewTask {
	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5 };
		System.out.println(productWithoutI(array, 4));

	}

	public static long productWithoutI(int[] array, int i) {
		if (i >= array.length) {
			// exception
		}
		long product = 1;
		for (int j = 0; j < array.length; j++) {
			if (i == j) {
				continue;
			}
			product *= array[j];
		}
		return product;
	}
}
