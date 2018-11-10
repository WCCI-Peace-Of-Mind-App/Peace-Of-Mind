package org.wecancodeit.peaceofmind;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ContactInfo {
	
	@Id
	@GeneratedValue
	private long id;

	@OneToMany(mappedBy = "contactInfo")
	private Set<Address> addresses = new HashSet<>();
	
	@OneToMany(mappedBy = "contactInfo")
	private Set<Phone> phones = new HashSet<>();

	@OneToMany(mappedBy = "contactInfo")
	private Set<Email> emails = new HashSet<>();

	@OneToOne(mappedBy = "contactInfo", fetch = FetchType.LAZY, optional = false)
	private Patient patient;

	@OneToOne(mappedBy = "contactInfo", fetch = FetchType.LAZY, optional = false)
	private NonMedicalUser nonMedicalUser;

	@OneToOne(mappedBy = "contactInfo", fetch = FetchType.LAZY, optional = false)
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

	public void addAddress(Address address) {
		if(addresses.contains(address)) {
			return;
		}
		this.addresses.add(address);
		address.setContactInfo(this);
	}

	public void removeAddress(Address address) {
		if (!addresses.contains(address)) {
			return;
		}
		this.addresses.remove(address);
		address.setContactInfo(null);
	}
	

	public void addPhone(Phone phone) {
		if(phones.contains(phone)) {
			return;
		}
		this.phones.add(phone);
		phone.setContactInfo(this);
	}

	public void removePhone(Phone phone) {
		if (!phones.contains(phone)) {
			return;
		}
		this.phones.remove(phone);
		phone.setContactInfo(null);
	}

	public void addEmail(Email email) {
		if(emails.contains(email)) {
			return;
		}
		this.emails.add(email);
		email.setContactInfo(this);
	}

	public void removeEmail(Email email) {
		if (!emails.contains(email)) {
			return;
		}
		this.emails.remove(email);
		email.setContactInfo(null);
	}

}
