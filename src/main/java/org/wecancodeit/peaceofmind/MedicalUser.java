package org.wecancodeit.peaceofmind;

public class MedicalUser implements Person {

	private String name;
	private String address;
	private String contactInfo;
	private String medicalSpecialty;
	private String medicalInstitution;
	private String institutionTelephone;
	private String userName;
	private String password;

	public MedicalUser(String name, String address, String contactInfo, String medicalSpecialty, String medicalInstitution, String institutionTelephone, String userName, String password) {
		super();
		this.name = name;
		this.address = address;
		this.contactInfo = contactInfo;
		this.medicalSpecialty = medicalSpecialty;
		this.medicalInstitution = medicalInstitution; 
		this.institutionTelephone = institutionTelephone;
		this.userName = userName;
		this.password = password;
	}

	public String getName() {

		return this.name;
	}

	public String getAddress() {

		return this.address;
	}

	public String getContact() {

		return this.contactInfo;
	}

	public String getMedicalSpecialty() {

		return this.medicalSpecialty;
	}

	public String getMedInstitution() {
		
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
