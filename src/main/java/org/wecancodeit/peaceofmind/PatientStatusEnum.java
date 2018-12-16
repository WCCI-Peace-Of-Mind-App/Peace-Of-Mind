package org.wecancodeit.peaceofmind;

public enum PatientStatusEnum {
	HAPPY ("happiness.png"), 
	SAD ("tearful.png"), 
	ANGRY ("angry.png"), 
	CONFUSED ("confused.png");
	
	private final String imageFile;
	
	PatientStatusEnum(String imageFile) {
		this.imageFile = imageFile;
	}
	
	public String getImageFile() {
		return imageFile;
	}
	
}
