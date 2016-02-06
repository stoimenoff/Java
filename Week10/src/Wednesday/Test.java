package Wednesday;

import java.util.concurrent.ExecutionException;

public class Test {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		int elements = 2000000;
		int capacity = 100;
		int maxProducers = 8;
		int maxConsumers = 8;

		Tool tool = new Tool(elements, capacity, 1, 1);

		MeasurementsArray measures = new MeasurementsArray();

		for (int producers = 1; producers <= maxProducers; producers++) {

			for (int consumers = 1; consumers <= maxConsumers; consumers++) {

				tool.reset(elements, capacity, producers, consumers);
				measures.add(tool.measure());
				// measures.add(tool.measureAverage(10));
				System.out.println(measures.get(measures.size() - 1).getFullInfo());
			}
			System.out.println();
		}

		// sort and print measurements

		measures.sortByTime();
		System.out.println("Sorted by time: ");
		System.out.println(measures);

		measures.sortByProducers();
		System.out.println("Sorted by producers: ");
		System.out.println(measures);

		measures.sortByTime();
		measures.sortByConsumers();
		System.out.println("Sorted by consumers: ");
		System.out.println(measures);
		
		WriteMeasuresToFile.write(measures);

	}
}
