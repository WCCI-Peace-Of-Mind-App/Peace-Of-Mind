package org.wecancodeit.peaceofmind;

public class MedicalUser implements INonPatientUser {

	private String address;
	private String contactInfo;
	private String medicalSpecialty;
	private String medicalInstitution;
	private String institutionTelephone;
	private String userName;
	private String password;

	public MedicalUser(String firstName,String lastName, String address, String contactInfo, String medicalSpecialty,
			String medicalInstitution, String institutionTelephone, String userName, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.contactInfo = contactInfo;
		this.medicalSpecialty = medicalSpecialty;
		this.medicalInstitution = medicalInstitution; 
		this.institutionTelephone = institutionTelephone;
		this.userName = userName;
		this.password = password;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}
    @Override
	public String getLastName() {
		return lastName;
	}

	private String firstName;
	private String lastName;

	public String getAddress() {

		return this.address;
	}

	public String getContact() {

		return this.contactInfo;
	}

	public String getMedicalSpecialty() {

		return this.medicalSpecialty;
	}

	public String getMedicalInstitution() {
		
		return this.medicalInstitution;
	}

	public String getInstitutionTelephone() {

		return this.institutionTelephone;
	}

	/* (non-Javadoc)
	 * @see org.wecancodeit.peaceofmind.INonPatientUser#getUserName()
	 */
	@Override
	public String getUserName() {

		return this.userName;
	}

	/* (non-Javadoc)
	 * @see org.wecancodeit.peaceofmind.INonPatientUser#getPassword()
	 */
	@Override
	public String getPassword() {

		return this.password;
	}

}
