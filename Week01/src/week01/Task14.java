package week01;

public class Task14 {
	public static void main(String[] args){
		int[] ar = {1, 4, 2, 1, 4, 4, 4};
		System.out.println(maxSpan(ar));
	}
	public static int maxSpan(int[] numbers){
		int maxSpan = 0, j = 0;
		for(int i = 0; i < numbers.length; i++){
			for(j = numbers.length - 1; j >= 0;  j--){
				if(numbers[j] == numbers[i]){
					break;
				}
			}
			if(j - i + 1 > maxSpan){
				maxSpan = j - i + 1;
			}
		}
		return maxSpan;
	}
}
