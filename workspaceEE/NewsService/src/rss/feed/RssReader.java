package rss.feed;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public final class RssReader {
	
	private RssReader() {	
	}
	
	public static String getHtmlFromRssFeed(URL link) throws IOException {
		StringBuilder html = new StringBuilder();
		HttpURLConnection httpcon = (HttpURLConnection)link.openConnection();
		// Reading the feed
		SyndFeedInput input = new SyndFeedInput();
		
		SyndFeed feed = null;
		try {
			feed = input.build(new XmlReader(httpcon));
		} catch (IllegalArgumentException | FeedException e) {
		}
		
		List<SyndFeed> entries = feed.getEntries();
		Iterator<SyndFeed> itEntries = entries.iterator();
		
		while (itEntries.hasNext()) {
			SyndEntry entry = (SyndEntry) itEntries.next();
			html.append("<a href=\"" + entry.getLink() + "\">" + "<h3>" + entry.getTitle() + "</h3>" + "</a>");
			html.append("<p>" + entry.getDescription().getValue() + "</p>\n");
			html.append(entry.getAuthor() + "\n");
			html.append(entry.getPublishedDate().toString());
		
		}
		return html.toString();
	}
	
}
