package parallelMinimalPointsCalculations;

import java.util.Random;

public class Point {
	private int mX;
	private int mY;

	public Point(int x, int y) {
		mX = x;
		mY = y;
	}
	
	public double getDistanceTo(Point p) {
		double distance = 0;
		distance += (mX - p.getX())*(mX - p.getX());
		distance += (mY - p.getY())*(mY - p.getY());
		distance = Math.sqrt(distance);
		return distance;
	}
	
	public int getY() {
		return mY;
	}

	public int getX() {
		return mX;
	}
	
	@Override
	public String toString() {
		StringBuilder builder  = new StringBuilder();
		builder.append("(" + mX + ", " + mY + ")");
		return builder.toString();
	}
	
	public static final Point[] generatePoints(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException();
		}
		Point[] points = new Point[size];
		Random rand = new Random();
		int x, y;
		for (int i = 0; i < points.length; i++) {
			x = rand.nextInt(10_000);
			y = rand.nextInt(10_000);
			points[i] = new Point(x, y);
		}
		return points;
	}

}
