package toDoList;

public class SleepTask {
	protected static double defaultPriority = 10f;
	protected static double duration = 8f;

	public String toString() {
		StringBuilder result = new StringBuilder("Sleep\n");
		result.append(super.toString());
		return result.toString();
	}
}
