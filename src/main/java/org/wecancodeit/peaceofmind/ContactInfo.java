package org.wecancodeit.peaceofmind;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ContactInfo {
	
	@Id
	@GeneratedValue
	private long id;

	@OneToMany(cascade = {CascadeType.ALL})
	private Collection<Address> addresses;
	
	@OneToMany(cascade = {CascadeType.ALL})
	private Collection<Phone> phones;
	
//	private Collection<String> emails;

	@OneToOne(mappedBy = "contactInfo")
	private Patient patient;
	
	public long getId() {
		return id;
	}
	
	
	
	public Collection<Address> getAddresses() {
		return addresses;
	}

	public Collection<Phone> getPhones() {
		return phones;
	}
	
//	public Collection<String> getEmails() {
//		return emails;
//	}
	public Patient getPatient() {
		return patient;
	}

	public ContactInfo() {}
	
	public ContactInfo(Collection<Address> addresses, Collection<Phone>phones, Collection<String> emails) {
		this.addresses = addresses;
		this.phones = phones;
//		this.emails = emails;
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
