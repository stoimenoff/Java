package weatherTool;

import java.io.IOException;
import java.net.URISyntaxException;

import org.codehaus.jettison.json.JSONException;

public class TestWeather {
	public static void main(String[] args) {
		try {
			//City city = new City(WeatherAPI.getCityWeather("Sofia"));
			//System.out.println(city);
			
			System.out.println(new City(WeatherAPI.getCityWeather("Sofia")));
			System.out.println(new City(WeatherAPI.getCityWeather("London")));
			
		} catch (JSONException | URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
