package Wednesday;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class Test {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		int elements = 2000000;
		int capacity = 100;
		Producer.toProduce(elements);
		Consumer.toConsume(elements);

		Tool tool = new Tool(elements, capacity, 1, 1);

		ArrayList<Measurement> measures = new ArrayList<Measurement>();

		for (int producers = 1; producers <= 8; producers++) {

			for (int consumers = 1; consumers <= 8; consumers++) {

				// reset tool, producers and consumers
				tool.reset(elements, capacity, producers, consumers);
				Producer.reset();
				Consumer.reset();
				// measure time
				measures.add(tool.measure());
				// print measurement
				System.out.println(measures.get(measures.size() - 1).getFullInfo());
			}
			System.out.println();
		}

		// sort and print measurements
		Collections.sort(measures);
		System.out.println("===============================Sorted===============================");
		for (Measurement m : measures) {
			System.out.println(m);
		}
	}
}
