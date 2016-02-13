package week01;

public class Task35 {
	public static boolean isCreditCardValid(String number) {
		if (number == null || number.length() % 2 == 0) {
			return false;
		}
		int sum = 0;
		for (int i = 0; i < number.length(); i++) {
			if(Character.isDigit(number.charAt(i))) {
				sum += Character.getNumericValue(number.charAt(i));
				if(i % 2 == 1) {
					sum += Character.getNumericValue(number.charAt(i));
				}
			} else {
				return false;
			}
		}
		return sum % 10 == 0;
	}
	public static void main(String[] args) {
		System.out.println(isCreditCardValid("79927398715"));
		System.out.println(isCreditCardValid("79927398713"));
	}
}
