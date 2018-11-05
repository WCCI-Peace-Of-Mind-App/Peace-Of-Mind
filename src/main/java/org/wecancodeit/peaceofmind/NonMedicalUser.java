package org.wecancodeit.peaceofmind;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class NonMedicalUser implements INonPatientUser {
	
	@Id
	@GeneratedValue
	private long id;

	private String firstName;
	private String lastName;
	@OneToOne
	private ContactInfo contactInfo;
	private String userName;
	private String password;
	private String relationshipWithPatient;


	public Long getId() {
		return id;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}
	
	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	@Override
	public String getUserName() {
		return userName;
	}
	@Override
	public String getPassword() {
		return password;
	}
	
	public String getRelationshipWithPatient() {
		return relationshipWithPatient;
	}
	
	public NonMedicalUser() {}
		
	public NonMedicalUser(String firstName, String lastName, ContactInfo contactInfo, String userName, String password, String relationshipWithPatient) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactInfo = contactInfo;
		this.userName = userName;
		this.password = password;
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
