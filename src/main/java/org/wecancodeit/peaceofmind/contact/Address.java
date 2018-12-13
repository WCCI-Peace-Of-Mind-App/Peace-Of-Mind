package org.wecancodeit.peaceofmind.contact;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Address implements IContactType{
	
	@Id
	@GeneratedValue
	private long id;

	@OneToOne(mappedBy="address")
	@JsonIgnore
	private ContactInfo contactInfo;
	
	private String streetAddress;
	private String secondaryField; 
	private String city;
	private String state;
	private String zipCode;
	private String type;

	public long getId() {
		return id;
	}
	
	public ContactInfo getContactInfo() {
		return contactInfo;
	}
	
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
		Address other = (Address) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String toString() {
		return type + " address: " + streetAddress + checkSecondaryField() + ", " + city + ", " + state + " " + zipCode;
	}
	
	private String checkSecondaryField() {
		if (secondaryField == null) {
			return "";
		} else {
			return ", " + secondaryField;
		}
	}


}