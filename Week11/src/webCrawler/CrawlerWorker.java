package webCrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrawlerWorker implements Callable<Set<URL>> {

	private Set<URL> mUrlMatches;
	private AutoCloseableBlockingQueue<URL> urls;
	private final String mNeedle;
	private final String mRootHost;

	public CrawlerWorker(AutoCloseableBlockingQueue<URL> q, String needle, String rootHost) {
		urls = q;
		mUrlMatches = new HashSet<URL>();
		mNeedle = needle;
		mRootHost = rootHost;
	}

	@Override
	public Set<URL> call() throws Exception {
		URL currentUrl = urls.poll();
		String content;
		while (currentUrl != null) {
			content = getContent(currentUrl);
			if (content.contains(mNeedle)) {
				mUrlMatches.add(currentUrl);
			} else {
				for (URL newUrl : getAllLinks(content)) {
					urls.add(newUrl);
				}
			}
			currentUrl = urls.poll();
		}
		System.out.println("Finished!");
		//System.out.println(mUrlMatches);
		return mUrlMatches;
	}

	private String getContent(URL url) {
		// TODO Insert APACHE code here
		String inputLine;
		StringBuilder content = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			while ((inputLine = reader.readLine()) != null) {
				content.append(inputLine);
			}
			reader.close();
		} catch (IOException e) {
		}
		return content.toString();
	}

	private ArrayList<URL> getAllLinks(String content) {
		ArrayList<URL> resultList = new ArrayList<>();
		String regex = "<a.*?href=\"((?!javascript).*?)\".*?>";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		URL newUrl = null;
		while (matcher.find()) {
			try {
				newUrl = new URL(matcher.group(1));
				//System.out.println(newUrl.getHost());
				if (newUrl.getHost().contains(mRootHost)) {
					System.out.println(newUrl);
					resultList.add(newUrl);
				}
			} catch (MalformedURLException e) {
				// e.printStackTrace();
			}
		}
		return resultList;
	}

}
