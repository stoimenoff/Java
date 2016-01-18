package week02;

public class Task19 {
	public static void main(String[] args) {
		
	}
	public static boolean IsIncreasing(int[] sequence) {
		if (sequence.length == 0)
		{
			return false;
		}
		for (int i = 0; i < sequence.length - 1; i++) {
			if (sequence[i] >= sequence[i + 1])
			{
				return false;
			}
		}
		return true;
	}
	public static boolean IsDecreasing(int[] sequence) {
		if (sequence.length == 0)
		{
			return false;
		}
		for (int i = 0; i < sequence.length - 1; i++) {
			if (sequence[i] <= sequence[i + 1])
			{
				return false;
			}
		}
		return true;
	}
}
