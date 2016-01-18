package statistics;

public class TestStatisticalCalculator {
	public static void main(String[] args) {
		StatisticalCalculator stat = new StatisticalCalculator();
		stat.add(11);
		stat.add(12);
		stat.add(13);
		stat.add(14);
		stat.add(15);
		stat.add(16);
		stat.add(17);
		stat.add(17);
		stat.add(18);
		stat.add(18);
		stat.add(18);
		stat.add(19);
		stat.add(10011);
		System.out.println(stat.getMean());
		System.out.println(stat.getMode());
		System.out.println(stat.getRange());
		System.out.println(stat.getMedian());
	}
}
