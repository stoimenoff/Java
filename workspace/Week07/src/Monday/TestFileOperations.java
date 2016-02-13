package Monday;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class TestFileOperations {
	@Test
	public void testRead() {
		String expected = "as\n" + "dgss df\n" + "fdsg fsdh dsg\n" + "asf g\n" + "sdfg\n" + "es hjrshjdfg\n" + " agh\n"
				+ "sdg es\n" + "gdffh 7  5247 y35\n" + "aey3\n" + "3 y357\n" + "346 34 6\n" + "37 3\n" + " 632 63\n";
		Path path = Paths.get("/home/stoimenoff/Desktop/testRead");
		try {
			assertEquals("Reading is OK?", expected, FileUtils.getInstance().readFrom(path));
		} catch (IOException e) {
			System.out.println("Cannot read file.");
		}
	}

	@Test
	public void testWrite() {
		String content = "as\n" + "dgss df\n" + "fdsg fsdh dsg\n" + "asf g\n" + "sdfg\n" + "es hjrshjdfg\n" + " agh\n"
				+ "sdg es\n" + "gdffh 7  5247 y35\n" + "aey3\n" + "3 y357\n" + "346 34 6\n" + "37 3\n" + " 632 63\n";
		Path path = Paths.get("/home/stoimenoff/Desktop/testRead");
		
		StringBuilder result = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(path.toFile()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				result.append(line);
				result.append(System.lineSeparator());
			}
		} catch (IOException e) {
			System.out.println("Can't read file");
		}
		
	}
}
