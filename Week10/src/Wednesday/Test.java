package Wednesday;

import java.util.concurrent.ExecutionException;

public class Test {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		int elements = 2000000;
		int capacity = 100;

		Tool tool = new Tool(elements, capacity, 1, 1);

		MeasurementsArray measures = new MeasurementsArray();

		for (int producers = 1; producers <= 8; producers++) {

			for (int consumers = 1; consumers <= 8; consumers++) {

				tool.reset(elements, capacity, producers, consumers);
				measures.add(tool.measure());
				// measures.add(tool.measureAverage(5));
				System.out.println(measures.get(measures.size() - 1).getFullInfo());
			}
			System.out.println();
		}

		// sort and print measurements
		//measures.sortByConsumers();
		measures.sortByTime();
		System.out.println("\nSorted: ");
		measures.print();

	}
}
