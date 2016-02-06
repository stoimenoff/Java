package parallelMinimalPointsCalculations;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class MinimalDistanceCalculator implements Callable<Map<Point, Point>> {

	private final Point[] mPoints;
	private final int mStart;
	private final int mEnd;
	private CountDownLatch mStartSignal;

	public MinimalDistanceCalculator(final Point[] points, int start, int end, CountDownLatch startSignal) {
		mPoints = points;
		mStart = start;
		mEnd = end;
		mStartSignal = startSignal;
	}

	@Override
	public Map<Point, Point> call() throws Exception {

		mStartSignal.await();

		HashMap<Point, Point> result = new HashMap<Point, Point>();
		int closestPoint;
		for (int currentPoint = mStart; currentPoint < mEnd; currentPoint++) {
			closestPoint = findClosestPointIndex(currentPoint);
			result.put(mPoints[currentPoint], mPoints[closestPoint]);
		}
		System.out.println(Thread.currentThread().getName() + " done!");
		return result;
	}

	private int findClosestPointIndex(int currentPoint) {
		double minDIstance = Double.MAX_VALUE;
		int closestPoint = -1;
		double distance;
		for (int point = 0; point < mPoints.length; point++) {
			if (point == currentPoint) {
				continue;
			}
			distance = mPoints[currentPoint].getDistanceTo(mPoints[point]);
			if (distance < minDIstance) {
				minDIstance = distance;
				closestPoint = point;
			}
		}
		return closestPoint;
	}

}
