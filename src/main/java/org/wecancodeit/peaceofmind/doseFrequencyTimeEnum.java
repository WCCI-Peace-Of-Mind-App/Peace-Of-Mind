package org.wecancodeit.peaceofmind;

public enum doseFrequencyTimeEnum {
Daily ("daily"), 
Weekly ("weekly"), 
Monthly ("monthly"), 
As_Needed ("needed");
	
	private String text;
	
	private doseFrequencyTimeEnum(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}
	
	public static doseFrequencyTimeEnum fromString(String text) {
		for (doseFrequencyTimeEnum d : doseFrequencyTimeEnum.values()) {
			if (d.text.equalsIgnoreCase(text)) {
				return d;
			}
		}
		return null;
	}

}
