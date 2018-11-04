package org.wecancodeit.peaceofmind;

public class NonMedicalUser {

	private String firstName;
	private String lastName;
	private String address;
	private String contactInfo;
	private String workNumber;
	private String userName;
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getAddress() {
		return address;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public String getWorkNumber() {
		return workNumber;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public NonMedicalUser(String firstName, String lastName, String address, String contactInfo, String workNumber, String userName, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.contactInfo = contactInfo;
		this.workNumber = workNumber;
		this.userName = userName;
		this.password = password;
	}



}
