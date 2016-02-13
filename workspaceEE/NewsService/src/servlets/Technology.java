package servlets;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * Servlet implementation class Technology
 */
@WebServlet("/Technology")
public class Technology extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Technology() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		URL url = new URL("http://www.dnevnik.bg/rssc/?rubrid=1660");
		HttpURLConnection httpcon = (HttpURLConnection)url.openConnection();
		// Reading the feed
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = null;
		try {
			feed = input.build(new XmlReader(httpcon));
		} catch (IllegalArgumentException | FeedException e) {
		}
		List entries = feed.getEntries();
		Iterator itEntries = entries.iterator();
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		while (itEntries.hasNext()) {
			SyndEntry entry = (SyndEntry) itEntries.next();
			response.getWriter().write("<a href=\"" + entry.getLink() + "\">" + "<h3>" + entry.getTitle() + "</h3>" + "</a>");
			response.getWriter().write("<p>" + entry.getDescription().getValue() + "</p>\n");
			response.getWriter().write(entry.getAuthor() + "\n");
			response.getWriter().write(entry.getPublishedDate().toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
