package Wednesday;

import java.util.ArrayList;
import java.util.Arrays;

public class TestSorting {
	public static void main(String[] args) {
		Integer[] l = {8,8,6,3,1};
		ArrayList<Integer> la = new ArrayList<Integer>(Arrays.asList(l));
		FunnyArraySorter sorter = new FunnyArraySorter(2);
		sorter.pivotSubtractionSort(la);
		System.out.println(la);
	}
}
