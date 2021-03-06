package org.wecancodeit.peaceofmind.contact;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Email implements IContactType {
	
	@Id
	@GeneratedValue
	private long id;
	
	@OneToOne(mappedBy = "email")
	@JsonIgnore
	private ContactInfo contactInfo;
	
	private String emailAddress;
	private String type;
	
	public long getId() {
		return id;
	}
	
	public ContactInfo getContactInfo() {
		return contactInfo;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public String getType() {
		return type;
	}
	
	public Email() {}
	
	public Email(String emailAddress, String type) {
		this.emailAddress = emailAddress;
		this.type = type;
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
		Email other = (Email) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public String toString() {
		return type + " email: " + emailAddress;
	}



}
