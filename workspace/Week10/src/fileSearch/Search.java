package fileSearch;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Search {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		int searchersCount = 4;

		FilesQueue filesToBeSearched = new FilesQueue(1000);
		Path root = Paths.get("/home/stoimenoff/Desktop/TestSearch");

		Runnable barrierAction = new Runnable() {
			@Override
			public void run() {
				filesToBeSearched.allFilesQueued();
			}
		};
		CyclicBarrier allDirectoriesTraversed = new CyclicBarrier(1, barrierAction);

		ExecutorService pool = Executors.newCachedThreadPool();

		pool.execute(new DirectoryTraversor(root, filesToBeSearched, allDirectoriesTraversed));

		ArrayList<Future<Set<Match>>> futureSets = new ArrayList<Future<Set<Match>>>();

		for (int i = 0; i < searchersCount; i++) {
			Callable<Set<Match>> searcher = new FileSearcher(filesToBeSearched, "es");
			Future<Set<Match>> futureSet = pool.submit(searcher);
			futureSets.add(futureSet);
		}
		pool.shutdown();

		Set<Match> matches = new HashSet<Match>();
		for (Future<Set<Match>> futureSet : futureSets) {
			matches.addAll(futureSet.get());
		}

		for (Match m : matches) {
			System.out.println(m);
		}
		pool.shutdown();

	}
}
