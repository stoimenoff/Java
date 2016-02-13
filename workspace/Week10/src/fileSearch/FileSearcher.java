package fileSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

public class FileSearcher implements Callable<Set<Match>> {

	private HashSet<Match> mMatches;
	private FilesQueue mFilesToBeSearched;
	private String mPattern;

	public FileSearcher(FilesQueue filesToBeSearched, String pattern) {
		mFilesToBeSearched = filesToBeSearched;
		mMatches = new HashSet<Match>();
		mPattern = pattern;
	}

	@Override
	public Set<Match> call() throws Exception {
		Path file = null;
		while (mFilesToBeSearched.wontBeEmptyForever()) {
			file = mFilesToBeSearched.poll();
			if (file != null) {
				search(file);
			}
		}

		return mMatches;
	}

	private void search(Path file) {
		//System.out.println("Searching file: " + file);
		try (BufferedReader reader = Files.newBufferedReader(file, Charset.defaultCharset())) {
			String line = null;
			int row = 1;
			while ((line = reader.readLine()) != null) {
				if (line.contains(mPattern)) {
					mMatches.add(new Match(file, row));
				}
				row += 1;
			}
		} catch (IOException x) {
			//System.out.println("Cannot open file.");
		}
		//System.out.println("Finished searching file: " + file);
	}

}
