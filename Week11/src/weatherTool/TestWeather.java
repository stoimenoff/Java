package weatherTool;

import java.io.IOException;
import java.net.URISyntaxException;

import org.codehaus.jettison.json.JSONException;

public class TestWeather {
	public static void main(String[] args) {
		try {
			City london = new City(WeatherAPI.getCityWeather("London"));
			System.out.println(london);
		} catch (JSONException | URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
