package Wednesday;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriteMeasuresToFile {

	public static void write(MeasurementsArray measures) {
		// write to file
		Path pathToFile = Paths.get("/home/stoimenoff/Desktop/measure.txt");
		BufferedWriter fileWriter = null;
		try {
			fileWriter = Files.newBufferedWriter(pathToFile, Charset.defaultCharset());
			fileWriter.write(measures.fullString());

			measures.sortByTime();
			fileWriter.write("\nSorted by time: \n" + measures.toString());

			measures.sortByProducers();
			fileWriter.write("\nSorted by producers: \n" + measures.toString());

			measures.sortByTime();
			measures.sortByConsumers();
			fileWriter.write("\nSorted by consumers: \n" + measures.toString());

			if (fileWriter != null) {
				fileWriter.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
