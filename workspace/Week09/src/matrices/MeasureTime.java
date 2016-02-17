package matrices;

public class MeasureTime implements Runnable{
	
	long time;
	
	public MeasureTime(long t) {
		time = t;
	}
	
	@Override
	public void run() {
		System.out.print(System.currentTimeMillis() - time);
	}

}
