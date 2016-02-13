package week01;

public class Task15 {
	public static void main(String[] args){
		int[] ar = {2, 1, 1, 2, 1};
		System.out.println(canBalance(ar));
	}
	public static boolean canBalance(int[] numbers){
		long sum = 0, curSum = 0;
		for (int i = 0; i < numbers.length; i++) {
			sum += numbers[i];
		}
		if(sum % 2 == 1){
			return false;
		}
		for (int i = 0; i < numbers.length; i++) {
			curSum += numbers[i];
			if(curSum == sum - curSum){
				return true;
			}
		}
		return false;
	}
}
