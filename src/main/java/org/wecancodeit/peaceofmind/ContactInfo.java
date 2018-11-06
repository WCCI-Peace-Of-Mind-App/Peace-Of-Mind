package org.wecancodeit.peaceofmind;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ContactInfo {
	
	@Id
	@GeneratedValue
	private long id;

	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name="fk_contactInfo")
	private Collection<Address> addresses;
	
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name="fk_contactInfo")
	private Collection<Phone> phones;

	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name="fk_contactInfo")
	private Collection<Email> emails;

	@OneToOne(mappedBy = "contactInfo")
	private Patient patient;

	@OneToOne(mappedBy = "contactInfo")
	private NonMedicalUser nonMedicalUser;

	@OneToOne(mappedBy = "contactInfo")
	private MedicalUser medicalUser;
	
	public long getId() {
		return id;
	}
	
	public Collection<Address> getAddresses() {
		return addresses;
	}

	public Collection<Phone> getPhones() {
		return phones;
	}
	
	public Collection<Email> getEmails() {
		return emails;
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
	
	public ContactInfo(Collection<Address> addresses, Collection<Phone> phones, Collection<Email> emails) {
		this.addresses = addresses;
		this.phones = phones;
		this.emails = emails;
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
