package vatTaxCalculator;

import java.util.TreeSet;

public class CountryVatTax {
	private static TreeSet<Integer> countriesIds = new TreeSet<Integer>();

	private int countryId;
	private boolean isDefault;
	private double VATTax;

	public CountryVatTax(int id, double tax, boolean isDefault) {
		if (id < 0) {
			System.out.println("Cannot create country with negative id.");
			return;
		}
		if (countriesIds.contains(id)) {
			System.out.println("Country with this id already exists.");
			return;
		}
		if (tax < 0) {
			System.out.println("Cannot create country with negative tax.");
			return;
		}
		countryId = id;
		countriesIds.add(id);
		VATTax = tax;
		this.isDefault = isDefault;
	}
	
	//Ask for this stuff !!!
	@Override
	protected void finalize() throws Throwable {
		countriesIds.remove(countryId);
		super.finalize();
	}

	public CountryVatTax(int id, double tax) {
		this(id, tax, false);
	}

	public double getTax() {
		return VATTax;
	}

	public int getId() {
		return countryId;
	}

	public boolean isDefault() {
		return isDefault;
	}
}
