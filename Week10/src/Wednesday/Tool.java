package Wednesday;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Tool {

	private int mElements;
	private int mProducersCount;
	private int mConsumersCount;
	private int mCapacity;
	private Memory<Integer> memory;

	Tool(int elements, int capacity, int prod, int cons) {
		reset(elements, capacity, prod, cons);
	}

	public void reset(int elements, int capacity, int prod, int cons) {
		mCapacity = capacity;
		mProducersCount = prod;
		mConsumersCount = cons;
		mElements = elements;
		memory = new Memory<Integer>(capacity);
	}

	public Measurement measure() throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();

		ArrayList<Future<Integer>> produced = new ArrayList<Future<Integer>>();
		ArrayList<Future<Integer>> consumed = new ArrayList<Future<Integer>>();

		ExecutorService pool = Executors.newCachedThreadPool();

		for (int i = 0; i < mProducersCount; i++) {
			Callable<Integer> producer = new Producer(memory);
			Future<Integer> elementsProduced = pool.submit(producer);
			produced.add(elementsProduced);
		}
		for (int i = 0; i < mConsumersCount; i++) {
			Callable<Integer> producer = new Consumer(memory);
			Future<Integer> elementsConsumed = pool.submit(producer);
			consumed.add(elementsConsumed);
		}
		// get producers work
		int[] producersWork = new int[mProducersCount];
		int i = 0;
		for (Future<Integer> future : produced) {
			producersWork[i] = future.get();
			i++;
		}
		// get consumers work
		int[] consumersWork = new int[mConsumersCount];
		i = 0;
		for (Future<Integer> future : consumed) {
			consumersWork[i] = future.get();
			i++;
		}
		// get time
		long delta = System.currentTimeMillis() - start;
		// create measurement object
		Measurement m = new Measurement(mElements, mCapacity, producersWork, consumersWork, delta);
		return m;
	}

}
