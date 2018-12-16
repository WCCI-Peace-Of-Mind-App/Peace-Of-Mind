package org.wecancodeit.peaceofmind.users;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.wecancodeit.peaceofmind.contact.ContactInfo;

@Entity
public class MedicalUser extends AppUser {
 
	private String medicalSpecialty;
	private String medicalInstitution;
	private String institutionTelephone;

	@OneToOne
	private Patient patient;

	public MedicalUser() {}
	
	public MedicalUser(String firstName,String lastName, ContactInfo contactInfo, String medicalSpecialty,
			String medicalInstitution, String institutionTelephone, String userName, String password, Patient patient) {
		super(firstName, lastName, userName, password, contactInfo);
		this.medicalSpecialty = medicalSpecialty;
		this.medicalInstitution = medicalInstitution; 
		this.institutionTelephone = institutionTelephone;
		this.patient = patient;
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

	public Patient getPatient() {
		return patient;
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
