package weatherTool;

public class WeatherCondition {

	private final Integer id;
	private final String group;
	private final String description;
	private String icon;

	public WeatherCondition(Integer id, String g, String d, String i) {
		this.id = id;
		group = g;
		description = d;
		icon = i;
	}

	public Integer getId() {
		return id;
	}

	public String getGroup() {
		return group;
	}

	public String getDescription() {
		return description;
	}

	public String getIcon() {
		return icon;
	}

	public void changeToNight() {
		if (icon.endsWith("d")) {
			icon = icon.substring(0, 2) + 'n';
		}
	}

	public void changeToDay() {
		if (icon.endsWith("n")) {
			icon = icon.substring(0, 2) + 'd';
		}
	}
	
	@Override
	public String toString() {
		return group + ": " + description;
	}
	
}
