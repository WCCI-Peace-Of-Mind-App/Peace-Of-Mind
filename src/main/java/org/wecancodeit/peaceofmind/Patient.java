package org.wecancodeit.peaceofmind;

public class Patient {
	
	private String firstName;
	private String lastName;
	private String email; 
	private String phone;
	private String address;
	private String dateOfBirth;
	private String diagnosis;

	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhone() {
		return phone;
	}
	public String getAddress() {
		return address;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public String getDiagnosis() {
		return diagnosis;
	}

	public Patient(String firstName, String lastName, String email, String phone, String address, String dateOfBirth, String diagnosis) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.diagnosis = diagnosis;
	}


}
