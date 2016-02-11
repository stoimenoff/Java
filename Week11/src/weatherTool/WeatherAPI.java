package weatherTool;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public final class WeatherAPI {

	private WeatherAPI() {
	}

	public static JSONObject getCityWeather(String city)
			throws URISyntaxException, ClientProtocolException, IOException, JSONException {
		URI link = new URIBuilder()
					.setScheme("http")
					.setHost("api.openweathermap.org")
					.setPath("/data/2.5/weather")
					.setParameter("q", city)
					.setParameter("units", "metric")
					.setParameter("APPID", "7b28ae98a4eeb03faf9418be1103ae03")
					.build();
		String content = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			HttpGet getRequest = new HttpGet(link.toString());
			CloseableHttpResponse response = httpClient.execute(getRequest);

			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					content = EntityUtils.toString(entity, "UTF-8");
				}
			} finally {
				response.close();
			}
		} finally {
			httpClient.close();
		}
		// System.out.println(content);
		JSONObject json = new JSONObject(content);
		return json;
	}

}
