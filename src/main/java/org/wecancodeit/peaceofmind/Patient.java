package org.wecancodeit.peaceofmind;

public class Patient {
	
	private String firstName;
	private String lastName;
	private String contactInfo; 
	private String dateOfBirth;
	private String diagnosis;

	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
	public String getContactInfo() {
		return contactInfo;
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public String getDiagnosis() {
		return diagnosis;
	}

	public Patient(String firstName, String lastName, String contactInfo, String dateOfBirth, String diagnosis) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactInfo = contactInfo;
		this.dateOfBirth = dateOfBirth;
		this.diagnosis = diagnosis;
	}


}
