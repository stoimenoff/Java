package matrices;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class RowColumnMultiplier implements Runnable {

	int[][] mMatrix;
	int mStartRow;
	int mEndRow;
	CyclicBarrier mDone;
	int[][] resultMatrix;

	public RowColumnMultiplier(int[][] matrix, int startRow, int endRow, int[][] result, CyclicBarrier done) {
		mMatrix = matrix;
		mStartRow = startRow;
		mEndRow = endRow;
		mDone = done;
		resultMatrix = result;
	}

	@Override
	public void run() {
		int result = 0;
		for (int row = mStartRow; row < mEndRow; row++) {
			for (int col = 0; col < mMatrix.length; col++) {
				result = 0;
				for (int i = 0; i < mMatrix.length; i++) {
					result += mMatrix[row][i] * mMatrix[i][col];
				}
				resultMatrix[row][col] = result;
			}
			
		}
		try {
			mDone.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			System.out.println("Barrier await failed...");
		}
	}

}
