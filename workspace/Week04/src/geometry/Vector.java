package geometry;

public class Vector {
	private double[] coordinates;

	Vector(int n) {
		if (n <= 0) {
			System.out.println("Vector coordinates number must be greater than zero!");
			return;
		}
		coordinates = new double[n];
	}

	Vector(Vector other) {
		if (other == null) {
			// null pointer exception
			return;
		}
		coordinates = new double[other.getDimensionality()];
		for (int i = 0; i < other.getDimensionality(); i++) {
			coordinates[i] = other.getCoordinate(i);
		}
	}

	public int getDimensionality() {
		return this.coordinates.length;
	}

	public double getCoordinate(int i) {
		if (i < 0 || i >= coordinates.length) {
			return -1;
		}
		return this.coordinates[i];
	}

	public void setCoordinate(int index, double value) {
		if (index < 0 || index >= coordinates.length) {
			System.out.println("Invalid coordinate index.");
			return;
		}
		coordinates[index] = value;
	}

	public double getLength() {
		double length = 0;
		for (int i = 0; i < coordinates.length; i++) {
			length += coordinates[i] * coordinates[i];
		}
		length = Math.sqrt(length);
		return length;
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder("Vector(");
		for (int i = 0; i < coordinates.length - 1; i++) {
			output.append(coordinates[i]);
			output.append(", ");
		}
		output.append(coordinates[coordinates.length - 1]);
		output.append(")");
		return output.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof Vector) {
			if (((Vector) obj).getDimensionality() == coordinates.length) {
				for (int i = 0; i < coordinates.length; i++) {
					if (((Vector) obj).getCoordinate(i) != coordinates[i]) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int hash = 17;
		for (int i = 0; i < coordinates.length; i++) {
			hash = hash * 23 + new Double(coordinates[i]).hashCode();
		}
		return hash;
	}
	
	public void add(Vector vector) {
		if (canPerformMathOperation(vector)) {
			for (int i = 0; i < coordinates.length; i++) {
				coordinates[i] += vector.getCoordinate(i);
			}
		}
	}
	
	public void subtract(Vector vector) {
		if (canPerformMathOperation(vector)) {
			for (int i = 0; i < coordinates.length; i++) {
				coordinates[i] -= vector.getCoordinate(i);
			}
		}
	}
	
	public void multiply(double scalar) {
		for (int i = 0; i < coordinates.length; i++) {
			coordinates[i] *= scalar;
		}
	}
	
	public void divide(double scalar) {
		multiply(1/scalar);
	}
	
	public double dotProduct(Vector vector) {
		if (canPerformMathOperation(vector)) {
			double product = 0;
			for (int i = 0; i < coordinates.length; i++) {
				product += coordinates[i] * vector.getCoordinate(i);
			}
			return product;
		}
		//throw exception
		return Double.POSITIVE_INFINITY;
	}
	
	private boolean canPerformMathOperation(Vector vector) {
		if (vector == null) {
			System.out.println("Cannot perform operation with null argument.");
			return false;
		}
		if (vector.getDimensionality() != coordinates.length) {
			System.out.println("Cannot perform opeartionon vectors wtih different dimensionality.");
			return false;
		}
		return true;
	}
}
