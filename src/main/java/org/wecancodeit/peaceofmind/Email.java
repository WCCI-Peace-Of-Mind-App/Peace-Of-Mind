package org.wecancodeit.peaceofmind;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Email implements IContactType {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private ContactInfo contactInfo;
	
	private String emailAddress;
	private String type;
	


	public long getId() {

	return id;
	}
	
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public String getType() {
		return type;
	}
	
	public Email() {
		
	}
	
	public Email(String emailAddress, String type) {
		this.emailAddress = emailAddress;
		this.type = type;
	}


}
