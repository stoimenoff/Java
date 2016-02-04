package SynchronizedArray;

import java.util.ArrayList;

public class TestSyncArray {
	public static void main(String[] args) throws InterruptedException {
		long start = System.currentTimeMillis();
		int partitions = 8; 
		int size = 20000000;
		SyncArray sArr = new SyncArray(size, partitions);
		
		ArrayList<Producer> producers = new ArrayList<>(partitions);
		ArrayList<Thread> threads = new ArrayList<>(partitions);
		
		int chunk = size/partitions;
		
		for (int i = 0; i < partitions; i++) {
			producers.add(new Producer(sArr, i * chunk, (i + 1) * chunk));
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
