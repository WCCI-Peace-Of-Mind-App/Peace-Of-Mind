package org.wecancodeit.peaceofmind;

public class MedicalUser implements Person {

	private String firstName;
	private String lastName;
	private ContactInfo contactInfo;
	private String medicalSpecialty;
	private String medicalInstitution;
	private String institutionTelephone;
	private String userName;
	private String password;

	public MedicalUser(String firstName,String lastName, ContactInfo contactInfo, String medicalSpecialty,
			String medicalInstitution, String institutionTelephone, String userName, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactInfo = contactInfo;
		this.medicalSpecialty = medicalSpecialty;
		this.medicalInstitution = medicalInstitution; 
		this.institutionTelephone = institutionTelephone;
		this.userName = userName;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public ContactInfo getContactInfo() {
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

	public String getUserName() {
		return this.userName;
	}

	public String getPassword() {
		return this.password;
	}

}
