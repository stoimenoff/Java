package SynchronizedArray;

import java.util.ArrayList;

public class TestNotPartArray {
	public static void main(String[] args) throws InterruptedException {
		long start = System.currentTimeMillis();
		int partitions = 8;
		int size = 20000000;
		NotPartArray arr = new NotPartArray(size);
		
		ArrayList<ShitProducer> producers = new ArrayList<>(partitions);
		ArrayList<Thread> threads = new ArrayList<>(partitions);
		
		int chunk = size/partitions;
		
		for (int i = 0; i < partitions; i++) {
			producers.add(new ShitProducer(arr, i * chunk, (i + 1) * chunk));
			threads.add(new Thread(producers.get(i)));
			threads.get(i).start();
		}
		for (int i = 0; i < partitions; i++) {
			threads.get(i).join();
		}
		long stop = System.currentTimeMillis() - start;
		System.out.println(stop);
	}
}
