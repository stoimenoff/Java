package week01;

public class Task03 {
	public static void main(String[] args){
		
	}
	public static int min(int[] array){
		assert(array.length != 0);
		int min  = array[0];
		for(int i = 1; i < array.length; i++){
			if(min > array[i]){
				min = array[i];
			}
		}
		return min;
	}
}
