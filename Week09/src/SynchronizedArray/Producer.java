package SynchronizedArray;

public class Producer implements Runnable {

	private SyncArray array;
	private int mStart;
	private int mEnd;

	public Producer(SyncArray drop, int start, int end) {
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