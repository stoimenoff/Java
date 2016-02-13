package Wednesday;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FunnyArraySorter {

	private int pivot;

	FunnyArraySorter(int pivot) {
		this.pivot = pivot;
	}

	FunnyArraySorter() {
		this(0);
	}

	public static void reverseSort(List<Integer> list) {
		Collections.sort(list, new Comparator<Integer>() {
			public int compare(Integer left, Integer right) {
				return Integer.compare(right, left);
			}
		});
	}

	public void pivotSubtractionSort(List<Integer> list) {
		Collections.sort(list, new Comparator<Integer>() {
			public int compare(Integer left, Integer right) {
				return Integer.compare(left - pivot, right);
			}
		});
	}

	public void pivotDivisionSort(List<Integer> list) {
		if (pivot == 0) {
			// throw exception
			return;
		}
		Collections.sort(list, new Comparator<Integer>() {
			public int compare(Integer left, Integer right) {
				return Integer.compare(left / pivot, right);
			}
		});
	}
}
