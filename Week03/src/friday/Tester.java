package friday;

public class Tester {
	public static void main(String[] args) {
		HashMap<String, Integer> test = new HashMap<String, Integer>();
		test.put("Aedf", 2);
		test.put("Aedf", 3);
		//test.remove("Aedf");
		System.out.println(test.get("Aedf"));
		System.out.println(test.containsKey("Aedf"));
	}
}
