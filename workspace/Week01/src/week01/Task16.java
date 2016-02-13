package week01;

public class Task16 {
	public static void main(String[] args) {
		
	}
	int[][] rescale(int[][] original, int newWidth, int newHeight){
		int[][] scaled = new int[newHeight][newWidth];
		int height = original.length;
		int width = original[0].length;
		int approxI, approxJ;
		for (int i = 0; i < scaled.length; i++) {
			for (int j = 0; j < scaled[i].length; j++) {
				approxI = i*height/newHeight;
				approxJ = i*width/newWidth;
				scaled[i][j] = original[approxI][approxJ];
			}
		}
		return scaled;
	}
}
