package week01;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;

public class Task28 {
	public static void main(String[] args) {
		String path = "/home/stoimenoff/Desktop/11791893_1026695054008724_1851257401_n.jpg";
		convertToGreyscale(path);
	}

	public static void convertToGreyscale(String imgPath) {
		BufferedImage img = null;
		// read image
		try {
			img = ImageIO.read(new File(imgPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// convert to grayscale TODO
		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {

				int rgb = img.getRGB(i, j);
				int r = (rgb >> 16) & 0xFF;
				int g = (rgb >> 8) & 0xFF;
				int b = (rgb & 0xFF);

				int grayLevel = (r + g + b) / 3;
				int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel;
				img.setRGB(i, j, gray);
			}
		}
		// save image
		try {
			ImageIO.write(img, "jpg", new File(imgPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
