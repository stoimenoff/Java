package matrices;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MatrixMultiplier {
	public static void main(String[] args) {
		int nThreads = 8;
		int n = 1000;
		int[][] matrix = new int[n][n];
		int[][] resultMatrix = new int[n][n];
		Random rand = new Random();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				matrix[i][j] = rand.nextInt(100);
			}
		}
		long time = System.currentTimeMillis();
		Runnable collect = new MeasureTime(time);
		CyclicBarrier done = new CyclicBarrier(nThreads, collect);
		ExecutorService pool = Executors.newFixedThreadPool(nThreads);
		int startRow = 0;
		int chunk = n / nThreads;

		for (int i = 0; i < nThreads - 1; i++) {
			pool.execute(new RowColumnMultiplier(matrix, startRow, startRow + chunk, resultMatrix, done));
			startRow += chunk;
		}
		pool.execute(new RowColumnMultiplier(matrix, startRow, n, resultMatrix, done));
		pool.shutdown();
	}
}
