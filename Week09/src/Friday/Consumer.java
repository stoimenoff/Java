package Friday;

import java.util.Random;

public class Consumer implements Runnable {

	private BlockingQueue<Integer> queue;
	private int mToConsume;

	public Consumer(BlockingQueue<Integer> drop, int toConsume) {
		queue = drop;
		mToConsume = toConsume;
	}

	@Override
	public void run() {
		Random rand = new Random();
		while (mToConsume > 0) {
			try {
				queue.poll();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
			}
			mToConsume--;
			try {
				Thread.sleep(rand.nextInt(2000));
			} catch (InterruptedException e) {
			}
		}
	}
}