package week01;

public class Task12 {
	public static void main(String[] args){
		int[] ar = {1,2,2,1,3,4,3,4,4,6,5,6,5};
		System.out.println(getOddOccurrence(ar));
	}
	public static int getOddOccurrence(int[] array){
		int pos = 0, times = 0, swap = 0;
		for(int i = 0; i < array.length; i++){
			times = 0;
			for(int j = i; j < array.length; j++){
				if(array[i] == array[j]){
					times += 1;
					swap = array[pos];
					array[pos] = array[j];
					array[j] = swap;
					pos += 1;
				}
			}
			if(times % 2 == 1){
				return array[pos - 1];
			}
			i = pos - 1;
		}
		return -1;
	}
}
