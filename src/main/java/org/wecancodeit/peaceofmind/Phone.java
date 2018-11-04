package org.wecancodeit.peaceofmind;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Phone implements IContactType{

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name="fk_contactInfo")
	private ContactInfo contactInfo;
	
	private String phoneNumber;
	private String type;

	public Phone() {}
	
	public Phone(String phoneNumber, String phoneType) {
		this.phoneNumber = phoneNumber;
		this.type = phoneType;
	}

	public long getId() {
		return this.id;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public String getType() {
		return this.type;
	}
	
	public ContactInfo getContactInfo() {
		return this.contactInfo;
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
		Phone other = (Phone) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

}
