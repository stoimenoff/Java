package geometry;

public class Rectangle extends ArealessShape implements Shape {

	private Point upperLeft;
	private Point lowerRight;

	Rectangle(Point p1, Point p2) {
		if (p1 == null || p2 == null) {
			return;
		}
		if (p1.getX() >= p2.getX() || p1.getY() <= p2.getY()) {
			System.out.println("Points are on the same edge !");
			return;
		}
		upperLeft = new Point(p1);
		lowerRight = new Point(p2);
		double centerX = (upperLeft.getX() + lowerRight.getX()) / 2;
		double centerY = (upperLeft.getY() + lowerRight.getY()) / 2;
		center = new Point(centerX, centerY);
	}

	Rectangle(Rectangle other) {
		this(other.getUpperLeft(), other.getLowerRight());
	}

	public Point getUpperLeft() {
		return this.upperLeft;
	}

	public Point getLowerRight() {
		return this.lowerRight;
	}

	public Point getLowerLeft() {
		return new Point(upperLeft.getX(), lowerRight.getY());
	}

	public Point getUpperRight() {
		return new Point(lowerRight.getX(), upperLeft.getY());
	}

	public LineSegment getUpperEdge() {
		return Point.add(upperLeft, this.getUpperRight());
	}

	public LineSegment getLowerEdge() {
		return Point.add(this.getLowerLeft(), lowerRight);
	}

	public LineSegment getLeftEdge() {
		return Point.add(upperLeft, this.getLowerLeft());
	}

	public LineSegment getRightEdge() {
		return Point.add(this.getUpperRight(), lowerRight);
	}

	public double getWidth() {
		return Math.abs(lowerRight.getX() - upperLeft.getX());
	}

	public double getHeight() {
		return Math.abs(upperLeft.getY() - lowerRight.getY());
	}

	public double getPerimeter() {
		return 2 * (getWidth() + getHeight());
	}

	public double getArea() {
		return getWidth() * getHeight();
	}

	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("Rectangle: ");
		output.append(upperLeft.toString());
		output.append(", (");
		output.append(getHeight());
		output.append(", ");
		output.append(getWidth());
		output.append(")]");
		return output.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof Rectangle) {
			if (((Rectangle) obj).getUpperLeft().equals(upperLeft)) {
				if (((Rectangle) obj).getLowerRight().equals(lowerRight)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hash = 17;
		hash = hash * 23 + upperLeft.hashCode();
		hash = hash * 23 + lowerRight.hashCode();
		return hash;
	}
}
