package org.wecancodeit.peaceofmind;

public class NonMedicalUser {

	private String firstName;
	private String lastName;
	private String address;
	private String contactNumber;
	private String workNumber;
	private String username;
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

	public String getContactNumber() {
		return contactNumber;
	}

	public String getWorkNumber() {
		return workNumber;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public NonMedicalUser(String firstName, String lastName, String address, String contactNumber, String workNumber, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.contactNumber = contactNumber;
		this.workNumber = workNumber;
		this.username = username;
		this.password = password;
	}



}
