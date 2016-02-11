package secondWebCrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkTask implements Callable<Pair<URL, Integer>> {

	private ConcurrentHashMap<URL, Integer> loaded;
	private ConcurrentLinkedQueue<Future<Pair<URL, Integer>>> futurePairs;
	private ExecutorService pool;
	private URL link;
	private String needle;

	public LinkTask(URL l, String n, ExecutorService p, ConcurrentHashMap<URL, Integer> load,
			ConcurrentLinkedQueue<Future<Pair<URL, Integer>>> res) {
		link = l;
		loaded = load;
		futurePairs = res;
		needle = n;
		pool = p;
	}

	@Override
	public Pair<URL, Integer> call() throws InterruptedException {
		System.out.println(link);
		// get the content
		String content = getContent(link);
		// prepare a list of tasks
		ArrayList<LinkTask> newTasks = new ArrayList<LinkTask>();
		// iterate through the links on the page
		for (URL newUrl : getAllLinks(content)) {
			// if the link hasn't been loaded, prepare a task for it and mark it
			// as loaded
			if (!loaded.containsKey(newUrl)) {
				loaded.put(newUrl, 1);
				LinkTask newTask = new LinkTask(newUrl, needle, pool, loaded, futurePairs);
				newTasks.add(newTask);
			}
		}
		// execute the prepared tasks and add their results to the list of
		// futures
		futurePairs.addAll(pool.invokeAll(newTasks));
		// create a pair to return from this task
		Pair<URL, Integer> myPair = new Pair<URL, Integer>(link, 0);
		// if the content of the page, contains the searched needle, set the
		// value in the pair to 1
		if (content.contains(needle)) {
			// System.out.println("Match: " + link);
			myPair.setValue(1);
		}
		return myPair;
	}

	private String getContent(URL url) {
		// TODO Insert APACHE code here !!!
		String inputLine;
		StringBuilder content = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			while ((inputLine = reader.readLine()) != null) {
				content.append(inputLine);
			}
			reader.close();
		} catch (IOException e) {
			// System.out.println("NIE SME SMOTANI!");
		}
		return content.toString();
	}

	private ArrayList<URL> getAllLinks(String content) {
		//TODO make a better link filter
		ArrayList<URL> resultList = new ArrayList<>();
		String regex = "<a.*?href=\"((?!javascript).*?)\".*?>";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		URL newUrl = null;
		while (matcher.find()) {
			try {
				newUrl = new URL(matcher.group(1));
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
