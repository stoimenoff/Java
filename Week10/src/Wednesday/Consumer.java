package Wednesday;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements Runnable, Callable<Integer> {

	private Memory<Integer> memory;
	private int mConsumed;
	private int elements;
	private AtomicInteger mToConsume;

	public Consumer(Memory<Integer> drop, AtomicInteger counter, int toConsume) {
		memory = drop;
		mConsumed = 0;
		mToConsume = counter;
		elements = toConsume;
	}

	@Override
	public void run() {
		while (mToConsume.getAndIncrement() < elements) {
			try {
				memory.take();
				mConsumed += 1;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}
	}

	@Override
	public Integer call() throws Exception {
		run();
		return mConsumed;
	}
}
