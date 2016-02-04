package SynchronizedArray;

public class FillArray {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		int size = 20000000;
		Object[] arr = new Object[size];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new Integer(i);
		}
		long stop = System.currentTimeMillis() - start;
		System.out.println(stop);
	}
}
