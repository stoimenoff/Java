package json.pretty;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public final class Client {

	private Client() {
	}

	public static String prettyJson(String uglyJson) throws URISyntaxException, IOException {
		URI link = new URIBuilder()
				.setScheme("http")
				.setHost("localhost:8080")
				.setPath("/JSONPrettyService/Submit")
				.build();
		String prettyJson = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			HttpPost postRequest = new HttpPost(link.toString());
			
		    ArrayList <NameValuePair> params = new ArrayList <NameValuePair>();
		    params.add(new BasicNameValuePair("json", uglyJson));

		    postRequest.setEntity(new UrlEncodedFormEntity(params));

			CloseableHttpResponse response = httpClient.execute(postRequest);

			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					prettyJson = EntityUtils.toString(entity, "UTF-8");
				}
			} finally {
				response.close();
			}
		} finally {
			httpClient.close();
		}

		return prettyJson;
	}
	
	public static String getJsonFromApi (URL link) throws IOException {
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
	return content;
	}

}
