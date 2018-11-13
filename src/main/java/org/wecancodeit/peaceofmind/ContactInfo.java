package org.wecancodeit.peaceofmind;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ContactInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;


	@OneToOne
	private Address address;
	

	@OneToMany
	private Collection<Phone> phones;


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

	public Collection<Phone> getPhones() {
		return phones;
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
	
	public ContactInfo(Address address, Email email, Phone...phones) {
		this.address = address;
		this.email = email; 
		this.phones = new HashSet<>(Arrays.asList(phones));
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


}
