package org.wecancodeit.peaceofmind;

public class ContactInfo {

	private String address;
	private String phone;
	private String email;

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}
	
	public String getEmail() {
		return email;
	}

	public ContactInfo(String address, String phone, String email) {
		this.address = address;
		this.phone = phone;
		this.email = email;
	}


}
