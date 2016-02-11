package weatherTool;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public final class WeatherJSONParser {

	public static Double getLongitude(JSONObject json) throws JSONException {
		return json.getJSONObject("coord").getDouble("lon");
	}

	public static Double getLatitude(JSONObject json) throws JSONException {
		return json.getJSONObject("coord").getDouble("lat");
	}

	public static Integer getId(JSONObject json) throws JSONException {
		return json.getInt("id");
	}

	public static String getName(JSONObject json) throws JSONException {
		return json.getString("name");
	}

	public static String getCountryISO3166(JSONObject json) throws JSONException {
		return json.getJSONObject("sys").getString("country");
	}
	
	public static WeatherCondition getWeatherCondition(JSONObject json) {
		JSONArray weatherJSONArray;
		try {
			weatherJSONArray = json.getJSONArray("weather");
		} catch (JSONException e) {
			weatherJSONArray = null;
		}
		JSONObject weatherJSON;
		try {
			weatherJSON = weatherJSONArray.getJSONObject(0);
		} catch (JSONException e) {
			weatherJSON = null;
		}
		Integer id;
		try {
			id = weatherJSON.getInt("id");
		} catch (JSONException e) {
			id = null;
		}
		String main;
		try {
			main = weatherJSON.getString("main");
		} catch (JSONException e) {
			main = null;
		}
		String des;
		try {
			des = weatherJSON.getString("description");
		} catch (JSONException e) {
			des = null;
		}
		String icon;
		try {
			icon = weatherJSON.getString("icon");
		} catch (JSONException e) {
			icon = null;
		}

		return new WeatherCondition(id, main, des, icon);
	}
	
	public static WeatherStats getWeatherStats(JSONObject json) {
		Double t;
		try {
			t = json.getJSONObject("main").getDouble("temp");
		} catch (JSONException e) {
			t = null;
		}
		Double p;
		try {
			p = json.getJSONObject("main").getDouble("pressure");
		} catch (JSONException e) {
			p = null;
		}
		Integer h;
		try {
			h = json.getJSONObject("main").getInt("humidity");
		} catch (JSONException e) {
			h = null;
		}
		Double tmin;
		try {
			tmin = json.getJSONObject("main").getDouble("temp_min");
		} catch (JSONException e) {
			tmin = null;
		}
		Double tmax;
		try {
			tmax = json.getJSONObject("main").getDouble("temp_max");
		} catch (JSONException e) {
			tmax = null;
		}
		Double sl;
		try {
			sl = json.getJSONObject("main").getDouble("sea_level");
		} catch (JSONException e5) {
			sl = null;
		}
		Double gl;
		try {
			gl = json.getJSONObject("main").getDouble("grnd_level");
		} catch (JSONException e4) {
			gl = null;
		}
		Double s;
		try {
			s = json.getJSONObject("wind").getDouble("speed");
		} catch (JSONException e3) {
			s = null;
		}
		Double d;
		try {
			d = json.getJSONObject("wind").getDouble("deg");
		} catch (JSONException e2) {
			d = null;
		}
		Double r;
		try {
			r = json.getJSONObject("rain").getDouble("3h");
		} catch (JSONException e1) {
			r = null;
		}
		Integer c;
		try {
			c = json.getJSONObject("clouds").getInt("all");
		} catch (JSONException e) {
			c = null;
		}
		return new WeatherStats(t, p, h, tmin, tmax, sl, gl, s, d, r, c);
	}

}
