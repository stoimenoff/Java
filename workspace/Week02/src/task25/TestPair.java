package task25;

import task25.Pair;

public class TestPair {
	public static void main(String[] args) {
		StringBuilder b1 = new StringBuilder("az sam 1");
		StringBuilder b2 = new StringBuilder("az sam 2");
		
		Pair<StringBuilder> p = new Pair<StringBuilder>(b1, b2);
		System.out.println(p.toString());
		b1.append(" Promenih 1");
		
		System.out.println(p.toString());
		
	}
}
