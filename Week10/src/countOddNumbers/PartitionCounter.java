package countOddNumbers;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class PartitionCounter implements Callable<Integer> {

	private ArrayList<Integer> mNumbers;
	private int mStart;
	private int mEnd;

	public PartitionCounter(ArrayList<Integer> nums, int start, int end) {
		mNumbers = nums;
		mEnd = end;
		mStart = start;
	}

	@Override
	public Integer call() throws Exception {
		int odds = 0;
		for (int i = mStart; i < mEnd; i++) {
			//if (mNumbers.get(i) % 2 == 1) {
			if ((mNumbers.get(i) & 1) == 1) {
				odds += 1;
			}
		}
		return odds;
	}

}
