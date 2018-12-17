package org.wecancodeit.peaceofmind.users;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import org.wecancodeit.peaceofmind.contact.ContactInfo;

@Entity
public class NonMedicalUser extends AppUser {
	
	private String relationshipWithPatient;
	
	@OneToOne(mappedBy = "nonMedicalUser", fetch = FetchType.LAZY, optional = false)
	private Patient patient;

	public String getRelationshipWithPatient() {
		return relationshipWithPatient;
	}
	
	public Patient getPatient() {
		return patient;
	}

	public NonMedicalUser() {}
		
	public NonMedicalUser(String firstName, String lastName, ContactInfo contactInfo, String userName, String password, String relationshipWithPatient) {
		super(firstName, lastName, userName, password, contactInfo);
		this.relationshipWithPatient = relationshipWithPatient;
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
		NonMedicalUser other = (NonMedicalUser) obj;
		if (id != other.id)
			return false;
		return true;
	}



}
