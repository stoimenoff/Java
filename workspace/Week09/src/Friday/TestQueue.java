package Friday;

public class TestQueue {
	public static void main(String[] args) {
		BlockingQueue<Integer> queue = new BlockingQueue<Integer>(5);
		(new Thread(new Producer(queue, 2))).start();
		// (new Thread(new Producer(queue, 42))).start();

		(new Thread(new Consumer(queue, 1))).start();
		(new Thread(new Consumer(queue, 1))).start();
	}
}