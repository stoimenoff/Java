package countOddNumbers;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class CountOddNumbers {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ArrayList<Integer> numbers = new ArrayList<Integer>();
		Random rand = new Random();
		for (int i = 0; i < 20_000_000; i++) {
			numbers.add(rand.nextInt());
		}
		
		System.out.println("Put");
		
		final int MAX_THREADS = 16;
		
		for (int threads = 1; threads <= MAX_THREADS; threads++) {
			System.out.println("Threads: " + threads + " Time: " + TestThreads.measureAverageTime(numbers, MAX_THREADS, 100));
		}
	}
}
