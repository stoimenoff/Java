package SynchronizedArray;

public class ShitProducer implements Runnable {

	private NotPartArray array;
	private int mStart;
	private int mEnd;

	public ShitProducer(NotPartArray drop, int start, int end) {
		array = drop;
		mStart = start;
		mEnd = end;
	}

	@Override
	public void run() {
		for (int i = mStart; i < mEnd; i++) {
			array.set(i, new Integer(i));
		}
	}
}