package vatTaxCalculator;

import java.util.HashMap;
import java.util.List;

public class VATTaxCalculator {
	private HashMap<Integer, CountryVatTax> countries;
	private int defaultId;

	public VATTaxCalculator(CountryVatTax[] countries) {
		this.countries = new HashMap<Integer, CountryVatTax>();
		for (int i = 0; i < countries.length; i++) {
			this.countries.put(countries[i].getId(), countries[i]);
			if (countries[i].isDefault()) {
				defaultId = countries[i].getId();
			}
		}
	}

	public VATTaxCalculator(List<CountryVatTax> countries) {
		this(countries.toArray(new CountryVatTax[countries.size()]));
	}

	public double CalculateTax(double price, int countryId) {
		if (countries.containsKey(countryId)) {
			double tax = countries.get(countryId).getTax();
			return tax * price;
		}
		// throw NotSupportedCountryException
		return -1;
	}

	public double CalculateTax(double price) {
		return CalculateTax(price, defaultId);
	}
}
