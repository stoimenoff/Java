package week01;

public class Task18 {
	public static void main(String[] args) {
		String s = "\t I am a loooong sentance     bla \n blabla.";
		System.out.println(reverseEveryChar(s));
	}
	public static String reverseEveryChar(String arg){
		StringBuilder reversedWords = new StringBuilder();
		int end = 0;
		for(int i = 0; i < arg.length(); i++){
			if(Character.isLetter(arg.charAt(i))){
				end = i + 1;
				while(end < arg.length() && Character.isLetter(arg.charAt(end))){
					end += 1;
				}
				for(int j = end - 1; j >= i; j--){
					reversedWords.append(arg.charAt(j));
				}
				i = end - 1;
			}
			else{
				reversedWords.append(arg.charAt(i));
			}
		}
		return reversedWords.toString();
	}
}
