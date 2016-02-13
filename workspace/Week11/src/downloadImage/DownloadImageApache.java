package downloadImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class DownloadImageApache {
	
	public static void main(String[] args) throws Exception{
		
		String url = "http://www.newton.ac.uk/files/covers/968361.jpg";
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet request = new HttpGet(url);

		CloseableHttpResponse response = client.execute(request);
		try {
			InputStream stream = response.getEntity().getContent();
			BufferedImage img = ImageIO.read(stream);
			
			File outputFile = new File("/home/stoimenoff/Desktop/apache_image.jpg");
			ImageIO.write(img, "jpg", outputFile);
			
		} finally {
			response.close();
		}
		
		System.out.println("Done!");
	}

}
