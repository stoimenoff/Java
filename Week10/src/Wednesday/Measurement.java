package Wednesday;

import java.util.ArrayList;

public class Measurement implements Comparable<Measurement> {
	private final int mElements;
	private final long mTime;
	private final int mCapacity;
	private final int[] mProducersWork;
	private final int[] mConsumersWork;
	private final int mAverage;
	
	public Measurement(int elements, int capacity, int[] producers, int[] consumers, long time, int average) {
		mTime = time;
		mElements = elements;
		mCapacity = capacity;
		mProducersWork = new int[producers.length];
		mConsumersWork = new int[consumers.length];
		mAverage = average;
		for (int i = 0; i < mConsumersWork.length; i++) {
			mConsumersWork[i] = consumers[i];
		}
		for (int i = 0; i < mProducersWork.length; i++) {
			mProducersWork[i] = producers[i];
		}
	}
	
	public Measurement(int elements, int capacity, int[] producers, int[] consumers, long time) {
		this(elements, capacity, producers, consumers, time, 1);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (mAverage > 1) {
			builder.append("Measures: " + mAverage);
		}
		builder.append(" Elements: " + mElements);
		builder.append(" Capacity: " + mCapacity);
		builder.append(" Producers: " + mProducersWork.length);
		builder.append(" Consumers: " + mConsumersWork.length);
		builder.append(" Time: " + mTime);
		// double ratio = (double)mProducersWork.length/mConsumersWork.length;
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

	// get functions

	public long getTime() {
		return mTime;
	}

	public int getElements() {
		return mElements;
	}

	public int getProducersCount() {
		return mProducersWork.length;
	}

	public int getProducerWork(int index) {
		if (index < 0 || index >= mProducersWork.length) {
			throw new IllegalArgumentException();
		}
		return mProducersWork[index];
	}
	
	public int getConsumerWork(int index) {
		if (index < 0 || index >= mConsumersWork.length) {
			throw new IllegalArgumentException();
		}
		return mConsumersWork[index];
	}
	
	public int getConsumersCount() {
		return mConsumersWork.length;
	}

	public int getCapacity() {
		return mCapacity;
	}
	
	// get average

	public static Measurement getAverage(ArrayList<Measurement> measures) {
		int size = measures.size();
		int prodSize = measures.get(0).getProducersCount();
		int consSize = measures.get(0).getConsumersCount();
		//check if can get average
		for (int i = 0; i < size; i++) {
			if (!canGetAverage(measures.get(0), measures.get(i))) {
				return null;
			}
		}
		//get average
		int elements = measures.get(0).getElements();
		int capacity = measures.get(0).getCapacity();
		long averageTime = 0;
		int[] averageProducersWork = new int[prodSize];
		int[] averageConsumersWork = new int[consSize];
		
		for (int i = 0; i < size; i++) {
			averageTime += measures.get(i).getTime();
			for (int j = 0; j < prodSize; j++) {
				averageProducersWork[j] += measures.get(i).getProducerWork(j);
			}
			for (int j = 0; j < consSize; j++) {
				averageConsumersWork[j] += measures.get(i).getConsumerWork(j);
			}
		}
		
		averageTime /= size;
		for (int j = 0; j < prodSize; j++) {
			averageProducersWork[j] /= size;
		}
		for (int j = 0; j < consSize; j++) {
			averageConsumersWork[j] /= size;
		}
		return new Measurement(elements, capacity, averageProducersWork, averageConsumersWork, averageTime, size);
	}

	private static boolean canGetAverage(Measurement m1, Measurement m2) {
		if (m1.getCapacity() != m2.getCapacity()) {
			return false;
		}
		if (m1.getConsumersCount() != m2.getConsumersCount()) {
			return false;
		}
		if (m1.getProducersCount() != m2.getProducersCount()) {
			return false;
		}
		if (m1.getElements() != m2.getElements()) {
			return false;
		}
		return true;
	}

}
