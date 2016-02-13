package parallelCopy;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Copier {
	public static void main(String[] args) throws IOException {
		
		int bigFile = 5000;
		Path root = Paths.get("/home/stoimenoff/Desktop/banan");
		Path destination = Paths.get("/home/stoimenoff/Desktop/NewFOLDERRRR");
		
		
		FilesProcessor processor = new FilesProcessor(root, bigFile);
		
		int bigFilesThreads = processor.getBigFiles().size();
		if (bigFilesThreads > 4) {
			bigFilesThreads = 4;
		}
		int smallFilesThreads = (int)(processor.getSmallFilesSize() / bigFile);
		if (smallFilesThreads > 4) {
			smallFilesThreads = 4;
		}
		
		Runnable barrierAction = new Runnable() {
			@Override
			public void run() {
				System.out.println("Done !");
			}
		};
		CyclicBarrier done = new CyclicBarrier(bigFilesThreads + smallFilesThreads, barrierAction);
		
		ExecutorService pool = Executors.newFixedThreadPool(bigFilesThreads + smallFilesThreads);
		
		for (int i = 0; i < bigFilesThreads; i++) {
			pool.execute(new FilesCopier(destination, root, processor.getBigFiles(), done));
		}
		
		for (int i = 0; i < smallFilesThreads; i++) {
			pool.execute(new FilesCopier(destination, root, processor.getSmallFiles(), done));
		}
		
		pool.shutdown();
		
	}
}
