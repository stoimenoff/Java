package Friday;

import java.util.Random;

public class Producer implements Runnable {

	private BlockingQueue<Integer> queue;
	private int mToProduce;

	public Producer(BlockingQueue<Integer> drop, int toProduce) {
		queue = drop;
		mToProduce = toProduce;
	}

	@Override
	public void run() {
		Random rand = new Random();
		while(mToProduce > 0) {
			try {
				queue.add(new Integer(6));
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
			}
			mToProduce--;
			try {
				Thread.sleep(rand.nextInt(2000));
			} catch (InterruptedException e) {
			}
		}
	}
}