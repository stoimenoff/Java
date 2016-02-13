package SynchronizedArray;

public class FillArray {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		int size = 200_000_000;
		int[] arr = new int[size];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		long stop = System.currentTimeMillis() - start;
		System.out.println(stop);
	}
}
