package week01;

public class Task21 {
	public static void main(String[] args) {
		String s = "dfgh";
		System.out.println(copyEveryChar(s, 5));
	}
	public static String copyEveryChar(String input, int k){
		StringBuilder mult = new StringBuilder();
		for(int i = 0; i < input.length(); i++){
			for(int j = 0; j < k; j++){
				mult.append(input.charAt(i));
			}
		}
		return mult.toString();
	}
}
