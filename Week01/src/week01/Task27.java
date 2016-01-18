package week01;

public class Task27 {
	public static void main(String[] args) {
		String a = "anagrama", b = "gramaaasdasfsafasfna";
		System.out.println(anagram(a, b));
	}
	public static boolean anagram(String A, String B){
		int[] hashMap = new int[256];
		for(int i = 0; i < B.length();i++){
			hashMap[B.charAt(i)] += 1;
		}
		for(int i = 0; i < A.length();i++){
			if(hashMap[A.charAt(i)] == 0){
				return false;
			}
			hashMap[A.charAt(i)] -= 1;
		}
		return true;
	}
}
