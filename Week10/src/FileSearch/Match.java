package FileSearch;

import java.nio.file.Path;

public class Match {
	private final Path mFile;
	private final int mLine;

	public Match(Path file, int line) {
		mFile = file;
		mLine = line;
	}

	public Path getFile() {
		return mFile;
	}

	public int getLine() {
		return mLine;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("File: " + mFile.toString() + " Line: " + mLine);
		return builder.toString();
	}
}
