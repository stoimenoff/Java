package Wednesday;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer implements Runnable, Callable<Integer> {

	static AtomicInteger mToConsume = new AtomicInteger(0);
	static int elements = 0;

	static boolean toConsume(int size) {
		if (size > 0) {
			if (elements == 0 || mToConsume.getAndAdd(0) >= elements) {
				elements = size;
				reset();
				return true;
			}
		}
		return false;
	}

	static void reset() {
		mToConsume.set(0);
	}

	private Memory<Integer> memory;
	private int mConsumed;

	public Consumer(Memory<Integer> drop) {
		memory = drop;
		mConsumed = 0;
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
