package org.wecancodeit.peaceofmind;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Address implements IContactType{
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private ContactInfo contactInfo;
	
	private String streetAddress;
	private String secondaryField; 
	private String city;
	private String state;
	private String zipCode;
	private String type;

	public String getStreetAddress() {
		return streetAddress;
	}
	
	public String getSecondaryField() {
		return secondaryField;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}
	
	public String getZipCode() {
		return zipCode;
	}
	
	public String getType() {
		return type;
	}

	public Address() {}
	
	public Address(String streetAddress, String secondaryField, String city, String state, String zipCode, String type) {
		this.streetAddress = streetAddress;
		this.secondaryField = secondaryField;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.type = type;
	}


}