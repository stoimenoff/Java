package statistics;

import java.util.ArrayList;
import java.util.HashMap;

public class StatisticalCalculator implements Statistics {

	private int size;
	private int minValue;
	private int maxValue;
	private int mode;
	private int modeFrequency;
	private double mean;
	private MaxHeap<Integer> firstHalf;
	private MinHeap<Integer> secondHalf;
	HashMap<Integer, Integer> occurences;

	public StatisticalCalculator() {
		occurences = new HashMap<Integer, Integer>();
		mean = 0;
		size = 0;
		minValue = Integer.MAX_VALUE;
		maxValue = Integer.MIN_VALUE;
		mode = Integer.MAX_VALUE;
		modeFrequency = 0;
		secondHalf = new MinHeap<Integer>();
		firstHalf = new MaxHeap<Integer>();
	}

	public void add(int number) {
		size += 1;
		if (occurences.size() > 0) {
			if (number > maxValue) {
				maxValue = number;
			}
			if (number < minValue) {
				minValue = number;
			}
			int count = 1;
			if (occurences.containsKey(number)) {
				count = occurences.get(number) + 1;
			}
			occurences.put(number, count);
			if (count > modeFrequency) {
				mode = number;
				modeFrequency = count;
			}
			// update mean: avoid overflowing the Integer
			mean /= size;
			mean *= (size - 1);
			mean += ((double)number / size);
			// update median heaps
			if (secondHalf.top().compareTo(number) == 1) {
				firstHalf.add(number);
			} else {
				secondHalf.add(number);
			}
			//balance heaps
			if (firstHalf.size() > secondHalf.size()) {
				secondHalf.add(firstHalf.pop());
			}
			if (secondHalf.size() - firstHalf.size() == 2) {
				firstHalf.add(secondHalf.pop());
			}
		} else {
			minValue = number;
			maxValue = number;
			mode = number;
			modeFrequency = 1;
			occurences.put(number, 1);
			mean = number;
			secondHalf.add(number);
		}
	}

	public double getMean() {
		return mean;
	}

	public int getMedian() {
		return secondHalf.top();
	}

	public int getMode() {
		if (size > 0) {
			return mode;
		} else {
			// throw exception
			return Integer.MAX_VALUE;
		}
	}

	public int getRange() {
		if (size > 0) {
			return maxValue - minValue;
		} else {
			// throw exception
			return Integer.MAX_VALUE;
		}
	}

}
