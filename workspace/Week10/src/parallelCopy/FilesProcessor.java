package parallelCopy;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FilesProcessor {
	
	private final Path mRoot;
	private ConcurrentLinkedQueue<Path> mBigFiles;
	private long mBigFilesSize;
	private ConcurrentLinkedQueue<Path> mSmallFiles;
	private long mSmallFilesSize;
	private final long mBig;
	
	
	public FilesProcessor(Path root, long big) {
		mRoot = root;
		mBigFiles = new ConcurrentLinkedQueue<Path>();
		mBigFilesSize = 0;
		mSmallFiles = new ConcurrentLinkedQueue<Path>();
		mSmallFilesSize = 0;
		mBig = big;
		process();
	}
	
	private void process() {
		Queue<Path> dirs = new LinkedList<Path>();
		dirs.add(mRoot);
		Path dir = null;
		while(!dirs.isEmpty()) {
			dir = dirs.poll();
			try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
				for (Path child : stream) {
					File childFile = new File(child.toUri());
					if (childFile.isDirectory()){
						dirs.add(child);
					} else {
						if (childFile.length() < mBig) {
							mSmallFiles.add(child);
							mSmallFilesSize += childFile.length();
						}
						else {
							mBigFiles.add(child);
							mBigFilesSize += childFile.length();
						}
					}
					
				}
			} catch (IOException | DirectoryIteratorException x) {
				System.err.println(x);
			}
		}
	}
	
	public ConcurrentLinkedQueue<Path> getBigFiles() {
		return mBigFiles;
	}

	public ConcurrentLinkedQueue<Path> getSmallFiles() {
		return mSmallFiles;
	}

	public long getBigFilesSize() {
		return mBigFilesSize;
	}
	
	public long getSmallFilesSize() {
		return mSmallFilesSize;
	}
	
}
