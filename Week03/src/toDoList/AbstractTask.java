package toDoList;

public abstract class AbstractTask implements Task {
	protected double priority;
	protected String status;
	protected static double defaultPriority;
	protected static double duration;

	AbstractTask(double priority) {
		this.priority = priority;
		this.status = "Active";
	}

	AbstractTask() {
		this(defaultPriority);
	}

	@Override
	public void finish() {
		this.status = "Finished";
	}

	@Override
	public void cancel() {
		this.status = "Cancelled";

	}

	@Override
	public double getPriority() {
		return this.priority;
	}

	@Override
	public String getStatus() {
		return this.status;
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Duration: " + duration + "\n");
		result.append("Priority: " + priority + "\n");
		result.append("Status: " + status + "\n");
		return result.toString();
	}

}