package downloadImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

public class DownloadImage {
	public static void main(String[] args) throws Exception {
		URL image = new URL("http://www.newton.ac.uk/files/covers/968361.jpg");

		BufferedImage img = ImageIO.read(image);
		File outputFile = new File("/home/stoimenoff/Desktop/image.jpg");

		ImageIO.write(img, "jpg", outputFile);

		System.out.println("Done !");
	}

}
