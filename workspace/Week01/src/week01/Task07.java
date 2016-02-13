package week01;

public class Task07 {
	public static void main(String[] args){
		System.out.println(kthFac(2, 3));
	}
	public static long fac(long n){
		long fact = 1;
		for(int i = 2; i <= n; i++){
			fact *= i;
		}
		return fact;
	}
	public static long kthFac(int k, int n){
		long kthFac = n;
		for(int i = 0; i < k; i++){
			kthFac = fac(kthFac);
		}
		return kthFac;
	}
}
