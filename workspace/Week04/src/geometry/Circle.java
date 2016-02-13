package geometry;

public class Circle extends ArealessShape implements Shape {
	private double radius;

	Circle(double radius, Point center) {
		if (center == null) {
			// null pointer exception
			return;
		}
		if (radius <= 0) {
			System.out.println("Cannot create circle with non-positive radius.");
			return;
		}
		this.radius = radius;
		this.center = new Point(center);
	}

	Circle(Circle other) {
		this(other.getRadius(), other.getCenter());
	}

	public double getRadius() {
		return radius;
	}

	public Point getUpperLeft() {
		double x = center.getX() - radius;
		double y = center.getY() + radius;
		return new Point(x, y);
	}

	public Point getLowerRight() {
		double x = center.getX() + radius;
		double y = center.getY() - radius;
		return new Point(x, y);
	}

	public Point getLowerLeft() {
		double x = center.getX() - radius;
		double y = center.getY() - radius;
		return new Point(x, y);
	}

	public Point getUpperRight() {
		double x = center.getX() + radius;
		double y = center.getY() + radius;
		return new Point(x, y);
	}
	
	@Override
	public double getPerimeter() {
		return 2 * Math.PI * radius;
	}

	@Override
	public double getArea() {
		return Math.PI * radius * radius;
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("Circle [");
		output.append(center.toString());
		output.append(", ");
		output.append(radius);
		output.append("]");
		return output.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof Circle) {
			if (((Ellipse) obj).getCenter().equals(center)) {
				if (((Circle) obj).getRadius() == radius) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hash = 17;
		hash = hash * 23 + center.hashCode();
		hash = hash * 23 + new Double(radius).hashCode();
		return hash;
	}
}
