package Wednesday;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable, Callable<Integer> {

	private Memory<Integer> memory;
	private int mProduced;
	private int elements;
	private AtomicInteger mToProduce;

	public Producer(Memory<Integer> drop, AtomicInteger counter, int toProduce) {
		memory = drop;
		mProduced = 0;
		mToProduce = counter;
		elements = toProduce;
	}

	@Override
	public void run() {
		while (mToProduce.getAndIncrement() < elements) {
			try {
				memory.put(new Integer(mToProduce.addAndGet(0)));
				mProduced += 1;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}
	}

	@Override
	public Integer call() throws Exception {
		run();
		return mProduced;
	}
}
