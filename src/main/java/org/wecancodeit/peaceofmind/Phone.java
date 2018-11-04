package org.wecancodeit.peaceofmind;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Phone implements IContactType{

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private ContactInfo contactInfo;
	
	private String phoneNumber;
	private String type;

	public Phone() {}
	
	public Phone(String phoneNumber, String phoneType) {
		this.phoneNumber = phoneNumber;
		this.type = phoneType;
	}

	public String getPhoneNumber() {

		return this.phoneNumber;
	}

	public String getType() {

		return this.type;
	}

}
