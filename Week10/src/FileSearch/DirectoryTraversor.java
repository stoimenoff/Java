package FileSearch;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class DirectoryTraversor implements Runnable {

	private Path mRoot;
	private Queue<Path> mDirectories;
	private FilesQueue mFilesToBeSearched;
	private CyclicBarrier done;

	public DirectoryTraversor(Path root, FilesQueue files, CyclicBarrier doneBarrier) {
		mRoot = root;
		mFilesToBeSearched = files;
		mDirectories = new LinkedList<Path>();
		done = doneBarrier;
	}

	@Override
	public void run() {
		mDirectories.add(mRoot);
		Path currentDir = null;
		while (!mDirectories.isEmpty()) {
			currentDir = mDirectories.poll();
			try (DirectoryStream<Path> stream = Files.newDirectoryStream(currentDir)) {
				for (Path child : stream) {
					if (isDriectory(child)) {
						mDirectories.add(child);
					} else {
						mFilesToBeSearched.add(child);
						//System.out.println("File added to be searhed: " + child);
					}
				}
			} catch (IOException | DirectoryIteratorException | InterruptedException x) {
				System.err.println(x);
			}
		}
		try {
			done.await();
		} catch (InterruptedException | BrokenBarrierException e) {
		}
	}

	private boolean isDriectory(Path path) {
		File fileOnPath = new File(path.toUri());
		return fileOnPath.isDirectory();
	}

}
