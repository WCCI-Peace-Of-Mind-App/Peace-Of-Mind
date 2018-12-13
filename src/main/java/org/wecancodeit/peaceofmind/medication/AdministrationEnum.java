package org.wecancodeit.peaceofmind.medication;

public enum AdministrationEnum {
	
	ORAL,
	INHALER,
	INJECTION;
	
	public String toString() {
		return name().toLowerCase();
	}
}
