package parallelMinimalPointsCalculations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		int pointsCount = 100_000;
		int threads = 8;

		Point[] points = Point.generatePoints(pointsCount);
		System.out.println("Generated...");
		ArrayList<Future<Map<Point, Point>>> futureMaps = new ArrayList<Future<Map<Point, Point>>>();

		CountDownLatch startSignal = new CountDownLatch(1);

		ExecutorService pool = Executors.newCachedThreadPool();
		int start = 0;
		int end = 0;
		int chunk = pointsCount/threads;
		for (int i = 0; i < threads; i++) {
			start = i * chunk;
			end = (i + 1) * chunk;
			if (i == threads - 1) {
				end = points.length;
			}
			Callable<Map<Point, Point>> calc = new MinimalDistanceCalculator(points, start, end, startSignal);
			futureMaps.add(pool.submit(calc));
		}
		long time = System.currentTimeMillis();
		System.out.println("Start calculations...");
		startSignal.countDown();
		HashMap<Point, Point> closestPoints = new HashMap<Point, Point>();
		for (Future<Map<Point, Point>> futureMap : futureMaps) {
			closestPoints.putAll(futureMap.get());
		}
		time = System.currentTimeMillis() - time;
		System.out.println("Time: " + time);
		
		/*
		for (Map.Entry<Point, Point> entry : closestPoints.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
		*/
	}
}
