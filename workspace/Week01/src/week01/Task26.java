package week01;

public class Task26 {
	public static void main(String[] args) {
		String a = "anagrama", b = "gramaana";
		System.out.println(anagram(a, b));
	}
	public static boolean anagram(String A, String B){
		if(A.length() != B.length()){
			return false;
		}
		int[] hashMap = new int[256];
		for(int i = 0; i < A.length();i++){
			hashMap[A.charAt(i)] += 1;
		}
		for(int i = 0; i < B.length();i++){
			if(hashMap[B.charAt(i)] == 0){
				return false;
			}
			hashMap[B.charAt(i)] -= 1;
		}
		return true;
	}
}
