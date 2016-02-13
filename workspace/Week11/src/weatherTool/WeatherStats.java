package weatherTool;

public class WeatherStats {

	private Double temperature;
	private Double pressure;
	private Integer humidity;
	private Double minTemp;
	private Double maxTemp;
	private Double pressureAtSeaLevel;
	private Double pressureAtGroundLevel;

	private Double windSpeed;
	private Double windDegree;

	private Double rainInLast3Hours;

	private Integer clouds;

	public WeatherStats(Double temperature, Double pressure, Integer humidity, Double minTemp, Double maxTamp,
			Double pressureAtSeaLevel, Double pressureAtGroundLevel, Double windSpeed, Double windDegree,
			Double rainInLast3Hours, Integer clouds) {
		super();
		this.temperature = temperature;
		this.pressure = pressure;
		this.humidity = humidity;
		this.minTemp = minTemp;
		this.maxTemp = maxTamp;
		this.pressureAtSeaLevel = pressureAtSeaLevel;
		this.pressureAtGroundLevel = pressureAtGroundLevel;
		this.windSpeed = windSpeed;
		this.windDegree = windDegree;
		this.rainInLast3Hours = rainInLast3Hours;
		this.clouds = clouds;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getPressure() {
		return pressure;
	}

	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}

	public Integer getHumidity() {
		return humidity;
	}

	public void setHumidity(Integer humidity) {
		if (humidity >= 100 || humidity < 0) {
			throw new IllegalArgumentException();
		}
		this.humidity = humidity;
	}

	public Double getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(Double minTemp) {
		this.minTemp = minTemp;
	}

	public Double getMaxTamp() {
		return maxTemp;
	}

	public void setMaxTamp(Double maxTamp) {
		this.maxTemp = maxTamp;
	}

	public Double getPressureAtSeaLevel() {
		return pressureAtSeaLevel;
	}

	public void setPressureAtSeaLevel(Double pressureAtSeaLevel) {
		this.pressureAtSeaLevel = pressureAtSeaLevel;
	}

	public Double getPressureAtGroundLevel() {
		return pressureAtGroundLevel;
	}

	public void setPressureAtGroundLevel(Double pressureAtGroundLevel) {
		this.pressureAtGroundLevel = pressureAtGroundLevel;
	}

	public Double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public Double getWindDegree() {
		return windDegree;
	}

	public void setWindDegree(Double windDegree) {
		this.windDegree = windDegree;
	}

	public Double getRainInLast3Hours() {
		return rainInLast3Hours;
	}

	public void setRainInLast3Hours(Double rainInLast3Hours) {
		this.rainInLast3Hours = rainInLast3Hours;
	}

	public Integer getClouds() {
		return clouds;
	}

	public void setClouds(Integer clouds) {
		if (clouds >= 100 || clouds < 0) {
			throw new IllegalArgumentException();
		}
		this.clouds = clouds;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Temperature: " + temperature + " C\n");
		builder.append("Pressure: " + pressure + " hPa\n");
		builder.append("Humidity: " + humidity + " %\n");
		builder.append("Minimal temperature: " + minTemp + " C\n");
		builder.append("Maximal temperature: " + maxTemp + " C\n");
		builder.append("Pressure at sea level: " + pressureAtSeaLevel + " hPa\n");
		builder.append("Pressure at ground level: " + pressureAtGroundLevel + " hPa\n");
		builder.append("Wind speed: " + windSpeed + " m/s \n");
		builder.append("Wind direction in degrees: " + windDegree + " \n");
		builder.append("Rain in the last 3h: " + rainInLast3Hours + "l\n");
		builder.append("Clouds: " + clouds + " %\n");
		return builder.toString();
	}
	
}
