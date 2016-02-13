package parallelCopy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CyclicBarrier;

public class FilesCopier implements Runnable{

	ConcurrentLinkedQueue<Path> mFiles;
	Path mDestination;
	Path mRoot;
	CyclicBarrier mDoneSignal;
	
	public FilesCopier(Path dest, Path root, ConcurrentLinkedQueue<Path> bigFiles, CyclicBarrier done) {
		mFiles = bigFiles;
		mDoneSignal = done;
		mDestination = dest;
		mRoot = root;
	}
	
	@Override
	public void run() {
		Path fileToCopy = null;
		while(!mFiles.isEmpty()) {
			fileToCopy = mFiles.poll();
			if (fileToCopy == null) {
				continue;
			}
			try {
				Files.copy(fileToCopy, createDestinatonPath(fileToCopy));
			} catch (IOException e) {
				System.out.println("Unsuccessful copy...");
				System.out.println(e);
			}
		}
		//go to barrier
		try {
			mDoneSignal.await();
		} catch (InterruptedException | BrokenBarrierException e) {
		}
	}
	
	private Path createDestinatonPath(Path pathToFile) {
		Path diff = mRoot.getParent().relativize(pathToFile);
		File buffer = new File(mDestination.toUri());
		File destination = new File(buffer, diff.toString());
		destination.getParentFile().mkdirs();
		return destination.toPath();
	}
	
}
