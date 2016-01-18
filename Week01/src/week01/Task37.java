package week01;

public class Task37 {
	public static String reduceFilePath(String path) {
		if(path == null) {
			//throw exception
			return null;
		}
		char symbol;
		StringBuilder reducedPath = new StringBuilder();
		for (int i = 0; i < path.length(); i++) {
			symbol = path.charAt(i);
			if (symbol == '/') {
				if (reducedPath.length() > 0 && reducedPath.charAt(reducedPath.length() - 1) == '/') {
					continue;
				}
			}
			if (symbol == '.') {
				if (reducedPath.length() > 0 && reducedPath.charAt(reducedPath.length() - 1) == '/') {
					if (i + 1 < path.length() && path.charAt(i + 1) == '.') {
						int tmp = reducedPath.length() - 2;
						while (tmp > 0 && reducedPath.charAt(tmp) != '/') {
							tmp -= 1;
						}
						if (tmp >= 0) {
							reducedPath.setLength(tmp);
						}
						i++;
					}
					continue;
				}
			}
			reducedPath.append(symbol);
		}
		if (reducedPath.length() > 1 && reducedPath.charAt(reducedPath.length() - 1) == '/') {
			reducedPath.deleteCharAt(reducedPath.length() - 1);
		}
		return reducedPath.toString();
	}

	public static void main(String[] args) {
		System.out.println(reduceFilePath("/"));
		System.out.println(reduceFilePath("/srv/../"));
		System.out.println(reduceFilePath("/srv/www/htdocs/wtf/"));
		System.out.println(reduceFilePath("/srv/www/htdocs/wtf"));
		System.out.println(reduceFilePath("/srv/./././././"));
		System.out.println(reduceFilePath("/etc//wtf/"));
		System.out.println(reduceFilePath("/etc/../etc/../etc/../"));
		System.out.println(reduceFilePath("//////////////"));
		System.out.println(reduceFilePath("/../"));
	}
}
