package Monday;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public final class FileUtils {
	private static FileUtils instance = null;

	private FileUtils() {
	}

	public static FileUtils getInstance() {
		if (instance == null) {
			instance = new FileUtils();
		}
		return instance;
	}

	public String readFrom(File file) throws IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line);
				result.append(System.lineSeparator());
			}
		} finally {
			reader.close();
		}
		return result.toString();
	}

	public String readFrom(Path path) throws IOException {
		return readFrom(path.toFile());
	}

	public void writeTo(String content, File file) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(content);
		}
	}

	public void writeTo(String content, Path path) throws IOException {
		writeTo(content, path.toFile());
	}

}
