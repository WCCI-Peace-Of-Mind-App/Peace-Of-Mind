package org.wecancodeit.peaceofmind;

public class NonMedicalUser implements INonPatientUser {

	private String firstName;
	private String lastName;
	private String address;
	private String contactInfo;
	private String workNumber;
	private String userName;
	private String password;

	/* (non-Javadoc)
	 * @see org.wecancodeit.peaceofmind.IPerson#getFirstName()
	 */
	@Override
	public String getFirstName() {
		return firstName;
	}

	/* (non-Javadoc)
	 * @see org.wecancodeit.peaceofmind.IPerson#getLastName()
	 */
	@Override
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
	@Override
	public String getUserName() {
		return userName;
	}
	@Override
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
