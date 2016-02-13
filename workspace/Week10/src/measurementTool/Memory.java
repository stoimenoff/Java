package measurementTool;

public interface Memory<T> {
	public void put(T element) throws InterruptedException;
	public T take() throws InterruptedException;
}
