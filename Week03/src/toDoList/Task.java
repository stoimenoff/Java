package toDoList;

public interface Task {
	void finish();
	void cancel();
	double getPriority();
	String getStatus();
}
