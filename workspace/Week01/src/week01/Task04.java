package week01;

public class Task04 {
	public static void main(String[] args){
		
	}
	public static int kthMin(int k, int[] array){
		assert(array.length != 0);
		int min  = 0, swap = 0;
		for(int i = 0; i < k; i++){
			min = array[i];
			for(int j = i; j < array.length; j++){
				if(array[j] < array[min]){
					min = j;
				}
			}
			swap = array[i];
			array[i] = array[min];
			array[min] = swap;
		}
		return array[k - 1];
	}
}
