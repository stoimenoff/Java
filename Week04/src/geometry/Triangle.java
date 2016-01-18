package geometry;

public class Triangle extends ArealessShape implements Shape {

	private Point A;
	private Point B;
	private Point C;

	public Triangle(Point p1, Point p2, Point p3) {
		double abLength = Point.add(p1, p2).getLength();
		double bcLength = Point.add(p2, p3).getLength();
		double acLength = Point.add(p1, p3).getLength();
		if (abLength + acLength <= bcLength || acLength + bcLength <= abLength || abLength + bcLength <= acLength) {
			System.out.println("Point are on the same axis.");
			return;
		}
		A = new Point(p1);
		B = new Point(p2);
		C = new Point(p3);
		double centerX = (A.getX() + B.getX() + C.getX()) / 3;
		double centerY = (A.getY() + B.getY() + C.getY()) / 3;
		center = new Point(centerX, centerY);
	}

	public Triangle(Triangle other) {
		this(other.getA(), other.getB(), other.getC());
	}

	public Point getA() {
		return A;
	}

	public Point getB() {
		return B;
	}

	public Point getC() {
		return C;
	}

	public LineSegment getAB() {
		return Point.add(A, B);
	}

	public LineSegment getBC() {
		return Point.add(B, C);
	}

	public LineSegment getAC() {
		return Point.add(A, C);
	}

	public double getPerimeter() {
		double perimeter = 0;
		perimeter += getAB().getLength();
		perimeter += getBC().getLength();
		perimeter += getAC().getLength();
		return perimeter;
	}

	public double getArea() {
		double p = getPerimeter() / 2;
		double a = getBC().getLength();
		double b = getAC().getLength();
		double c = getAB().getLength();
		double area = Math.sqrt(p * (p - a) * (p - b) * (p - c));
		return area;
	}

	public double getHeightA() {
		return 2 * getArea() / getBC().getLength();
	}

	public double getHeightB() {
		return 2 * getArea() / getAC().getLength();
	}

	public double getHeightC() {
		return 2 * getArea() / getAB().getLength();
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("Triangle[");
		output.append(A.toString());
		output.append(", ");
		output.append(B.toString());
		output.append(", ");
		output.append(C.toString());
		output.append("]");
		return output.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof Triangle) {
			if (((Triangle) obj).getA().equals(A)) {
				if (((Triangle) obj).getB().equals(B)) {
					if (((Triangle) obj).getC().equals(C)) {
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
		hash = hash * 23 + A.hashCode();
		hash = hash * 23 + B.hashCode();
		hash = hash * 23 + C.hashCode();
		return hash;
	}

}
