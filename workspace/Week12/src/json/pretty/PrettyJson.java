package json.pretty;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;

public class PrettyJson {
	public static void main(String[] args) throws URISyntaxException, MalformedURLException, IOException {
		String city = "London";
		URI link = new URIBuilder()
				.setScheme("http")
				.setHost("api.openweathermap.org")
				.setPath("/data/2.5/weather")
				.setParameter("q", city)
				.setParameter("units", "metric")
				.setParameter("APPID", "44db6a862fba0b067b1930da0d769e98")
				.build();
		
		String uglyJson = Client.getJsonFromApi(link.toURL());
		String prettyJson = Client.prettyJson(uglyJson);
		
		System.out.println("JSON:");
		System.out.println(prettyJson);
		
	}
}
