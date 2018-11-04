package org.wecancodeit.peaceofmind;

public class Address {
	
	
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

	public Address(String streetAddress, String secondaryField, String city, String state, String zipCode, String type) {
		this.streetAddress = streetAddress;
		this.secondaryField = secondaryField;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.type = type;
	}


}