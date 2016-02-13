package secondWebCrawler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SecondCrawler {
	public static void main(String[] args) throws MalformedURLException, InterruptedException, ExecutionException {
		// URL root = new URL("http://abv.bg");
		// URL root = new URL("http://www.fmi.uni-sofia.bg/");
		// URL root = new URL("http://www.minfin.bg/");

		// set the root site to be searched and the needle
		URL root = new URL("http://ebusiness.free.bg/");
		String needle = "Револвираща";
		// create executor service
		ExecutorService pool = Executors.newCachedThreadPool();
		// loaded concurrent hash map holds the URLs that have been given to
		// tasks
		ConcurrentHashMap<URL, Integer> loaded = new ConcurrentHashMap<URL, Integer>();
		// queue for the future results
		ConcurrentLinkedQueue<Future<Pair<URL, Integer>>> futurePairs = new ConcurrentLinkedQueue<>();
		// set the root as loaded
		loaded.put(root, 1);
		// create the root task
		LinkTask task = new LinkTask(root, needle, pool, loaded, futurePairs);
		// execute the root task and add the result to the list of futures
		Future<Pair<URL, Integer>> firstPair = pool.submit(task);
		futurePairs.add(firstPair);

		// collect the results
		ArrayList<URL> results = new ArrayList<URL>();
		Pair<URL, Integer> currentPair = null;

		while (!futurePairs.isEmpty()) {
			currentPair = futurePairs.poll().get();
			if (currentPair.getValue().equals(1)) {
				results.add(currentPair.getKey());
			}
		}
		pool.shutdown();
		// print results
		System.out.println("\n\nRESULTS:");
		for (URL result : results) {
			System.out.println(result);
		}
	}
}
