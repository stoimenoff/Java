package Wednesday;

public class Measurement implements Comparable<Measurement> {
	private final int mElements;
	private final long mTime;
	private final int mCapacity;
	private final int[] mProducersWork;
	private final int[] mConsumersWork;

	public Measurement(int elements, int capacity, int[] producers, int[] consumers, long time) {
		mTime = time;
		mElements = elements;
		mCapacity = capacity;
		mProducersWork = new int[producers.length];
		mConsumersWork = new int[consumers.length];
		for (int i = 0; i < mConsumersWork.length; i++) {
			mConsumersWork[i] = consumers[i];
		}
		for (int i = 0; i < mProducersWork.length; i++) {
			mProducersWork[i] = producers[i];
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Elements: " + mElements);
		builder.append(" Capacity: " + mCapacity);
		builder.append(" Producers: " + mProducersWork.length);
		builder.append(" Consumers: " + mConsumersWork.length);
		builder.append(" Time: " + mTime);
		// double ratio = (double)mProd/mCons;
		// builder.append(" Ratio : " + ratio);
		return builder.toString();
	}

	public String getFullInfo() {
		String info = this.toString();
		StringBuilder builder = new StringBuilder(info + "\n");
		builder.append("\n\tProducers work: \n");
		for (int i = 1; i <= mProducersWork.length; i++) {
			builder.append("\t\tProducer " + i + ": " + mProducersWork[i - 1] + "\n");
		}
		builder.append("\tConsumers work: \n");
		for (int i = 1; i <= mConsumersWork.length; i++) {
			builder.append("\t\tConsumer " + i + ": " + mConsumersWork[i - 1] + "\n");
		}
		return builder.toString();
	}

	public long getTime() {
		return mTime;
	}

	@Override
	public int compareTo(Measurement o) {
		long oTime = o.getTime();
		if (oTime < mTime) {
			return 1;
		}
		if (oTime > mTime) {
			return -1;
		}
		return 0;
	}

}
