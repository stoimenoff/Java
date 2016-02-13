package week01;

public class Task08 {
	public static void main(String[] args){
		System.out.println(getSmallestMultiple(5));
	}
	public static long getSmallestMultiple(int upperBound){
		int sMultiple = 1;
		boolean[] eratostenus = new boolean[upperBound + 1];
		for(int i = 0; i < upperBound + 1; i++){
			eratostenus[i] = true;
		}
		int prime = 2, highestPower = 1;
		while(prime <= upperBound){
			while(prime <= upperBound && eratostenus[prime] == false){
				prime += 1;
			}
			highestPower = 1;
			while(highestPower * prime <= upperBound){
				highestPower *= prime;
			}
			sMultiple *= highestPower;
			
			for(int i = 2 * prime; i < upperBound + 1; i += prime){
				eratostenus[i] = false;
			}
			prime += 1;
		}
		return sMultiple;
	}
}
