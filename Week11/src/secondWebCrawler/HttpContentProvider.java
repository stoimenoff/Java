package secondWebCrawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public final class HttpContentProvider {

	private HttpContentProvider() {
	}

	public static String getContent(URL link) throws IOException {
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

	public static ArrayList<URL> getAllLinks(String content, URL link) {
		// TODO make a better link filter
		ArrayList<URL> resultList = new ArrayList<>();
		String regex = "<a.*?href=\"((?!javascript).*?)\".*?>";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		URL newUrl = null;
		String match = null;
		while (matcher.find()) {
			try {
				match = matcher.group(1);
				if (!match.startsWith("http") && !match.contains("..") && !match.contains("#")) {
					match = link.toString() + match;
				}
				newUrl = new URL(match);
				if (newUrl.toString().contains(link.getHost()) && !newUrl.toString().contains("#")) {
					resultList.add(newUrl);
				}
			} catch (MalformedURLException e) {
				// e.printStackTrace();
			}
		}
		return resultList;
	}

}
