package week01;

public class Task10 {
	public static void main(String[] args){
		//System.out.println(histogram());
	}
	public static int[] histogram(short[][] image){
		int[] result = new int[256];
		for(int i = 0; i < 256; i++){
			result[i] = 0;
		}
		for(int i = 0; i < image.length; i++){
			for(int j = 0; j < image[i].length; j++){
				result[ image[i][j] ] += 1;
			}
		}
		return result;
	}
}
