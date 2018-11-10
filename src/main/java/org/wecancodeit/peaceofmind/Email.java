package org.wecancodeit.peaceofmind;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Email implements IContactType {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_contactInfo")
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

	public void setContactInfo(ContactInfo contactInfo) {
		if(sameAsFormer(contactInfo)) {
			return;
		}
		ContactInfo oldContactInfo = this.contactInfo;
		this.contactInfo = contactInfo;
		if(oldContactInfo != null) {
			oldContactInfo.removeEmail(this);
		}
		if(contactInfo != null) {
			contactInfo.addEmail(this);
		}
	}
	
	private boolean sameAsFormer(ContactInfo newContactInfo) {
	    return contactInfo==null? newContactInfo== null : contactInfo.equals(newContactInfo);
	}

}
