package org.wecancodeit.peaceofmind;

public enum AdministrationEnum {
	
	ORAL,
	INHALER,
	INJECTION;
	
	public String toString() {
		return name().toLowerCase();
	}
}
