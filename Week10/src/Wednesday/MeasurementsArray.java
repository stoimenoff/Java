package Wednesday;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MeasurementsArray extends ArrayList<Measurement> {

	public void sortByTime() {
		Collections.sort(this);
	}

	public void sortByProducers() {
		Collections.sort(this, new Comparator<Measurement>() {
			@Override
			public int compare(Measurement m1, Measurement m2) {
				return new Integer(m1.getProducersCount()).compareTo(new Integer(m2.getProducersCount()));
			}
		});
	}

	public void sortByConsumers() {
		Collections.sort(this, new Comparator<Measurement>() {
			@Override
			public int compare(Measurement m1, Measurement m2) {
				return new Integer(m1.getConsumersCount()).compareTo(new Integer(m2.getConsumersCount()));
			}
		});
	}

	public void sortByCapacity() {
		Collections.sort(this, new Comparator<Measurement>() {
			@Override
			public int compare(Measurement m1, Measurement m2) {
				return new Integer(m1.getCapacity()).compareTo(new Integer(m2.getCapacity()));
			}
		});
	}

	public void sortByElements() {
		Collections.sort(this, new Comparator<Measurement>() {
			@Override
			public int compare(Measurement m1, Measurement m2) {
				return new Integer(m1.getElements()).compareTo(new Integer(m2.getElements()));
			}
		});
	}

	public void print() {
		for (Measurement m : this) {
			System.out.println(m);
		}
	}
	
	public void printAll() {
		for (Measurement m : this) {
			System.out.println(m.getFullInfo());
		}
	}

}
