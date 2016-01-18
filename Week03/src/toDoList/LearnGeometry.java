package toDoList;

public class LearnGeometry {
	protected static double defaultPriority = 5f;
	protected static double duration = 1.5f;
	public String toString() {
		StringBuilder result = new StringBuilder("Learn geometry\n");
		result.append(super.toString());
		return result.toString();
	}
}
