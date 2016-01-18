package toDoList;

public class StudyForAlgebraTask extends AbstractTask{
	protected static double defaultPriority = 5f;
	protected static double duration = 2f;
	public String toString() {
		StringBuilder result = new StringBuilder("Study for algebra\n");
		result.append(super.toString());
		return result.toString();
	}
}
