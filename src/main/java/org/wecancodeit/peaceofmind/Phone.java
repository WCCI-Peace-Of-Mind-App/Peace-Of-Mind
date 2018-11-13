package org.wecancodeit.peaceofmind;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Phone implements IContactType{

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="contactInfo_id")
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

	public ContactInfo getContactInfo() {
		return this.contactInfo;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public String getType() {
		return this.type;
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
