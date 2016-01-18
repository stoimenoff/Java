package week01;

public class Task24 {
	public static void main(String[] args) {
		String s = "kitten%20pic.jpg";
		System.out.println(decodeUrl(s));
	}
	public static String decodeUrl(String input){
		StringBuilder url = new StringBuilder();
		//verify input is valid URL
		for (int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == '%'){
				switch(input.substring(i+1, i+3)){
					case "3A": url.append(':'); break;
					case "20": url.append(' '); break;
					case "3D": url.append('?'); break;
					case "2F": url.append('/'); break;
					//default : throw invalid URL exception;
				}
				i += 2;
			}
			else{
				url.append(input.charAt(i));
			}
		}
		return url.toString();
	}

}
