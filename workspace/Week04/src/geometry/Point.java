package geometry;

public class Point {
	public final static int ORIGIN_X = 0;
	public final static int ORIGIN_Y = 0;
	private double x;
	private double y;

	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	Point() {
		this(ORIGIN_X, ORIGIN_Y);
	}

	Point(Point other) {
		this(other.getX(), other.getY());
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (other instanceof Point) {
			if (((Point) other).getX() == this.x) {
				if (((Point) other).getY() == this.y) {
					return true;
				}
			}

		}
		return false;
	}

	public String toString() {
		return "(" + Double.toString(this.x) + ", " + Double.toString(this.y) + ")";
	}

	public int hashCode() {
		int hash = 17;
		hash = hash * 23 + new Double(x).hashCode();
		hash = hash * 23 + new Double(y).hashCode();
		return hash;
	}
	
	public static LineSegment add(Point p1, Point p2) {
		if (p1 == null || p2 == null) {
			return null;
		}
		return new LineSegment(p1, p1);
	} 
}
