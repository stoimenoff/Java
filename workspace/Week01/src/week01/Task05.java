package week01;

public class Task05 {
	public static void main(String[] args){
		
	}
	public static int getAverage(int[] array){
		assert(array.length != 0);
		int sum = 0;
		for(int i = 0; i < array.length; i++){
			sum += array[i];
		}
		return sum/array.length;
	}
}
