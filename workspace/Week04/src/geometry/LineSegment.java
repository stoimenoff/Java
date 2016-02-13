package geometry;

public class LineSegment implements Comparable<LineSegment>{
	private Point start;
	private Point end;

	LineSegment(Point p1, Point p2) {
		if (p1.equals(p2)) {
			System.out.println("Cannot create a line segment with zero length !");
			return;
		} else {
			start = new Point(p1);
			end = new Point(p2);
		}
	}

	LineSegment(LineSegment other) {
		this(other.getStart(), other.getEnd());
	}

	public Point getStart() {
		return start;
	}

	public Point getEnd() {
		return end;
	}

	public double getLength() {
		double length = 0;
		double delta_x = start.getX() - end.getX();
		double delta_y = start.getY() - end.getY();
		length = Math.sqrt(delta_x * delta_x + delta_y * delta_y);
		return length;
	}
	

	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (other instanceof LineSegment) {
			if (((LineSegment) other).getStart().equals(this.start)) {
				if (((LineSegment) other).getEnd().equals(this.end)) {
					return true;
				}
			}
		}
		return false;
	}

	public String toString() {
		return "Line[" + start.toString() + ", " + end.toString() + "]";
	}

	public int hashCode() {
		int hash = 17;
		hash = hash * 23 + start.hashCode();
		hash = hash * 23 + end.hashCode();
		return hash;
	}

	@Override
	public int compareTo(LineSegment o) {
		if (o == null) {
			//throw exception
		}
		double length = this.getLength();
		double otherLength = ((LineSegment) o).getLength();
		if (length < otherLength) {
			return -1;
		}
		if (length > otherLength) {
			return 1;
		}
		if (length == otherLength) {
			return 0;
		}
		//throw exception !!!!!!!!!!!!!
		return -2;
	}
}
