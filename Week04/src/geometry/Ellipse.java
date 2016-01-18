package geometry;

public class Ellipse extends ArealessShape implements Shape {
	private double majorAxisLength;
	private double minorAxisLength;

	public Ellipse(double minorLen, double majorLen, Point center) {
		if (center == null) {
			// null pointer exception
			return;
		}
		if (minorLen <= 0 || majorLen <= 0) {
			System.out.println("Cannot create Ellipse with non-positive axis lengths");
			return;
		}
		majorAxisLength = majorLen;
		minorAxisLength = minorLen;
		this.center = new Point(center);
	}

	public Ellipse(Ellipse other) {
		this(other.getMinorAxisLength(), other.getMajorAxisLength(), other.getCenter());
	}

	public double getMajorAxisLength() {
		return majorAxisLength;
	}

	public double getMinorAxisLength() {
		return minorAxisLength;
	}

	public Point getUpperLeft() {
		double x = center.getX() - majorAxisLength;
		double y = center.getY() + minorAxisLength;
		return new Point(x, y);
	}

	public Point getLowerRight() {
		double x = center.getX() + majorAxisLength;
		double y = center.getY() - minorAxisLength;
		return new Point(x, y);
	}

	public Point getLowerLeft() {
		double x = center.getX() - majorAxisLength;
		double y = center.getY() - minorAxisLength;
		return new Point(x, y);
	}

	public Point getUpperRight() {
		double x = center.getX() + majorAxisLength;
		double y = center.getY() + minorAxisLength;
		return new Point(x, y);
	}

	public double getArea() {
		return minorAxisLength * majorAxisLength * Math.PI;
	}

	public double getPerimeter() {
		return 2 * Math.PI * Math.sqrt(minorAxisLength * minorAxisLength / 2 + majorAxisLength * majorAxisLength / 2);
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("Ellipse [");
		output.append(center.toString());
		output.append(", (");
		output.append(majorAxisLength);
		output.append(", ");
		output.append(minorAxisLength);
		output.append(")]");
		return output.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof Ellipse) {
			if (((Ellipse) obj).getCenter().equals(center)) {
				if (((Ellipse) obj).getMajorAxisLength() == majorAxisLength) {
					if (((Ellipse) obj).getMinorAxisLength() == minorAxisLength) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hash = 17;
		hash = hash * 23 + center.hashCode();
		hash = hash * 23 + new Double(minorAxisLength).hashCode();
		hash = hash * 23 + new Double(majorAxisLength).hashCode();
		return hash;
	}
}
