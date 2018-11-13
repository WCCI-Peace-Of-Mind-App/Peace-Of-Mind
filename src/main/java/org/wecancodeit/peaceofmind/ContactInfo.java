package org.wecancodeit.peaceofmind;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ContactInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;


	@OneToOne
	private Address address;
	

	@OneToOne
	private Phone phone;


	@OneToOne
	private Email email;

	@JsonIgnore
	@OneToOne(mappedBy = "contactInfo", fetch = FetchType.LAZY, optional = false)
	private Patient patient;

	@JsonIgnore
	@OneToOne(mappedBy = "contactInfo", fetch = FetchType.LAZY, optional = false)
	private NonMedicalUser nonMedicalUser;

	@JsonIgnore
	@OneToOne(mappedBy = "contactInfo", fetch = FetchType.LAZY, optional = false)
	private MedicalUser medicalUser;
	
	public long getId() {
		return id;
	}
	
	public Address getAddress() {
		return address;
	}

	public Phone getPhone() {
		return phone;
	}
	
	public Email getEmail() {
		return email;
	}
	
	public Patient getPatient() {
		return patient;
	}

	public NonMedicalUser getNonMedicalUser() {
		return nonMedicalUser;
	}
	
	public MedicalUser getMedicalUser() {
		return medicalUser;
	}
	
	public ContactInfo() {}
	
	public ContactInfo(Address address, Email email, Phone phone) {
		this.address = address;
		this.email = email; 
		this.phone = phone;
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
		ContactInfo other = (ContactInfo) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public void addAddress(Address newAddress) {
		address = newAddress;
		
	}

	public void removeAddress() {
		address = null;
		
	}

	public void addPhone(Phone newPhone) {
		phone = newPhone;
		
	}

	public void removePhone() {
		phone = null;
		
	}

	public void addEmail(Email newEmail) {
		email = newEmail;
		
	}

	public void removeEmail() {
		email = null;
		
	}


}
