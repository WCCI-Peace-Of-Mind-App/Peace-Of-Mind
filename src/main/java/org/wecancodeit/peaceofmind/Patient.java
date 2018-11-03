package org.wecancodeit.peaceofmind;

public class Patient {
	
	private String firstName;
	private String lastName;
	private String primaryPhone;
	private String primaryAddress;
	private String dateOfBirth;
	private String diagnosis;

	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getPrimaryPhone() {
		return primaryPhone;
	}
	public String getPrimaryAddress() {
		return primaryAddress;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public String getDiagnosis() {
		return diagnosis;
	}

	public Patient(String firstName, String lastName, String primaryPhone, String primaryAddress, String dateOfBirth, String diagnosis) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.primaryPhone = primaryPhone;
		this.primaryAddress = primaryAddress;
		this.dateOfBirth = dateOfBirth;
		this.diagnosis = diagnosis;
	}


}
