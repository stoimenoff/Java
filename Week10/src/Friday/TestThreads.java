package Friday;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestThreads {
	static long measureTime(ArrayList<Integer> numbers, int threads) throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();

		ArrayList<Future<Integer>> odds = new ArrayList<Future<Integer>>();

		ExecutorService pool = Executors.newFixedThreadPool(threads);

		int chunk = numbers.size() / threads;

		for (int i = 0; i < threads; i++) {
				Callable<Integer> task = null;
			if (i == threads - 1) {
				task = new PartitionCounter(numbers, i * chunk, numbers.size());
			} else {
				task = new PartitionCounter(numbers, i * chunk, (i + 1) * chunk);
			}
			odds.add(pool.submit(task));
		}

		pool.shutdown();

		int oddsNum = 0;
		for (Future<Integer> future : odds) {
			oddsNum += future.get();
		}
		//System.out.println(oddsNum);
		return System.currentTimeMillis() - start;
	}
	
	static long measureAverageTime(ArrayList<Integer> numbers, int threads, int count) throws InterruptedException, ExecutionException {
		long avgTime = 0;
		for (int i = 0; i < count; i++) {
			avgTime += measureTime(numbers, threads);
		}
		return avgTime / count;
	}
	
}
