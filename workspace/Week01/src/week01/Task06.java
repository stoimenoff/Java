package week01;

public class Task06 {
	public static void main(String[] args){
		System.out.println(doubleFac(3));
	}
	public static long fac(long n){
		long fact = 1;
		for(int i = 2; i <= n; i++){
			fact *= i;
		}
		return fact;
	}
	public static long doubleFac(int n){
		return fac(fac(n));
	}
}
