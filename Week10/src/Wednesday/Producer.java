package Wednesday;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable, Callable<Integer> {

	static AtomicInteger mToProduce = new AtomicInteger(0);
	static int elements = 0;

	static boolean toProduce(int size) {
		if (size > 0) {
			if (elements == 0 || mToProduce.getAndAdd(0) >= elements) {
				elements = size;
				reset();
				return true;
			}
		}
		return false;
	}

	static void reset() {
		mToProduce.set(0);
	}

	private Memory<Integer> memory;
	private int mProduced;

	public Producer(Memory<Integer> drop) {
		memory = drop;
		mProduced = 0;
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
