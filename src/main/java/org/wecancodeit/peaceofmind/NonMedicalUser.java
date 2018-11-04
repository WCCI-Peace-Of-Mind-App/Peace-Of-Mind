package org.wecancodeit.peaceofmind;

public class NonMedicalUser {

	private String firstName;
	private String lastName;
	private ContactInfo contactInfo;
	private String userName;
	private String password;
	private String relationshipWithPatient;

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getRelationshipWithPatient() {
		return relationshipWithPatient;
	}

	public NonMedicalUser(String firstName, String lastName, ContactInfo contactInfo, String userName, String password, String relationshipWithPatient) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactInfo = contactInfo;
		this.userName = userName;
		this.password = password;
		this.relationshipWithPatient = relationshipWithPatient;
	}




}
