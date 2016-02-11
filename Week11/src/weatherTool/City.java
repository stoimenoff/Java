package weatherTool;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class City {

	private final Double longitude;
	private final Double latitude;
	private final String name;
	private final Integer id;
	private final String countryISO3166;

	private WeatherCondition weather;
	private WeatherStats stats;

	public City(JSONObject json) throws JSONException {
		longitude = WeatherJSONParser.getLongitude(json);
		latitude = WeatherJSONParser.getLatitude(json);
		id = WeatherJSONParser.getId(json);
		name = WeatherJSONParser.getName(json);
		weather = WeatherJSONParser.getWeatherCondition(json);
		stats = WeatherJSONParser.getWeatherStats(json);
		countryISO3166 = WeatherJSONParser.getCountryISO3166(json);
	}

	public Double getLongitude() {
		return longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public String getName() {
		return name;
	}

	public Integer getId() {
		return id;
	}

	public WeatherCondition weatherCondition() {
		return weather;
	}

	public WeatherStats WeatherStats() {
		return stats;
	}

	public String getCountryISO3166() {
		return countryISO3166;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("==============" + name + "==============" + "\n");
		builder.append("City: " + name + "\n");
		builder.append("Id: " + id + "\n");
		builder.append("Coordinates: " + longitude + ", " + latitude + "\n");
		builder.append("Country ISO 3166: " + countryISO3166 + "\n");
		builder.append("\n---------Weather---------" + "\n\n");
		builder.append(weather + "\n");
		builder.append("\n--------Statistics--------" + "\n\n");
		builder.append(stats);
		builder.append("==========================================" + "\n");
		return builder.toString();
	}

}
