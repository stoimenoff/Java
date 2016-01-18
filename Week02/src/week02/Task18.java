package week02;

public class Task18 {
	public static void main(String[] args) {
		System.out.println(nthLucas(5));
	}

	public static int nthLucas(int n) {
		if (n < 0) {
			return -1;
		}
		int nthLucas = 2;
		int nplus1Lucas = 1;
		for (int i = 0; i < n; i++) {
			nplus1Lucas += nthLucas;
			nthLucas = nplus1Lucas - nthLucas;
		}
		return nthLucas;
	}
}
