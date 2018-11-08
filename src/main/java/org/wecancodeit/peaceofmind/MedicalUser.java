package org.wecancodeit.peaceofmind;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class MedicalUser implements INonPatientUser {
 
	@Id
	@GeneratedValue
	private long id;

	private String firstName;
	private String lastName;
	@OneToOne(fetch = FetchType.LAZY)
	private ContactInfo contactInfo;
	private String medicalSpecialty;
	private String medicalInstitution;
	private String institutionTelephone;
	private String userName;
	private String password;

	public MedicalUser() {}
	
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

	public long getId() {
      return this.id;
	}

	@Override
	public String getFirstName() {
		return this.firstName;
	}
    @Override
	public String getLastName() {
		return this.lastName;
	}

	@Override
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

	@Override
	public String getUserName() {
		return this.userName;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public int hashCode() {
	  final int prime = 31;
	  int result = 1;
	  result = prime * result + (int) (id ^ (id >>> 32));
	  return result;
	}

	@Override
	public boolean equals(Object obj) {
	  if (this == obj)
		return true;
	  if (obj == null)
		return false;
	  if (getClass() != obj.getClass())
		return false;
	  MedicalUser other = (MedicalUser) obj;
	  if (id != other.id)
		return false;
	  return true;
	 }
}
