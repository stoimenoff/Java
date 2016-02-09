package webCrawler;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Crawler {
	public static void main(String[] args) throws Exception {
		int threads = 4;
		AutoCloseableBlockingQueue<URL> urls = new AutoCloseableBlockingQueue<URL>(1000, threads);
		URL root = new URL("http://abv.bg");
		String rootHost = root.getHost();
		urls.add(root);
		String needle = "Създаване";
		
		ExecutorService pool = Executors.newCachedThreadPool();
		
		ArrayList<Future<Set<URL>>> futureSets = new ArrayList<>();
		
		for (int i = 0; i < threads; i++) {
			Callable<Set<URL>> worker = new CrawlerWorker(urls, needle, rootHost);
			Future<Set<URL>> futureSet = pool.submit(worker);
			futureSets.add(futureSet);
		}
		pool.shutdown();
		
		HashSet<URL> matches = new HashSet<URL>();
		for(Future<Set<URL>> futureSet : futureSets) {
			matches.addAll(futureSet.get());
		}
		
		for(URL url : matches) {
			System.out.println(url);
		}
		
	}
}
