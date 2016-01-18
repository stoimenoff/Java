package toDoList;

public class GoOutTask {
	protected static double defaultPriority = 5f;
	protected static double duration = 2f;

	public String toString() {
		StringBuilder result = new StringBuilder("Go out\n");
		result.append(super.toString());
		return result.toString();
	}
}
