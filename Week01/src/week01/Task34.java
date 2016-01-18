package week01;

public class Task34 {
	public static int fridayYears(int startYear, int endYear) {
		int count = 0;
		int day = startsWith(startYear);
		for (int year = startYear; year <= endYear; year++) {
			if (day == 5 || (day == 4 && isLeap(year))) {
				count += 1;
			}
			day += 1;
			if (isLeap(year)) {
				day += 1;
			}
			if (day > 7) {
				day -= 7;
			}
		}
		return count;
	}

	private static int startsWith(int year) {
		// 2016 starts with Friday
		int day = 4;
		for (int i = 2016; i < year; i++) {
			day += 1;
			if (isLeap(i)) {
				day += 1;
			}
		}
		for (int i = 2016; i > year; i--) {
			day -= 1;
			if (isLeap(i - 1)) {
				day -= 1;
			}
			if (day < 0) {
				day = 7 + day;
			}
		}
		return day % 7 + 1;
	}

	private static boolean isLeap(int year) {
		if (year % 4 != 0) {
			return false;
		}
		if (year % 100 != 0) {
			return true;
		}
		if (year % 400 != 0) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(fridayYears(1000, 2000));
		System.out.println(fridayYears(1753, 2000));
		System.out.println(fridayYears(1990, 2015));
	}
}