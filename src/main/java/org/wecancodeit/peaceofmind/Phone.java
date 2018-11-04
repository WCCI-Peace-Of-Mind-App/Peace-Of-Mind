package org.wecancodeit.peaceofmind;

public class Phone {

	private String phoneNumber;
	private String phoneType;

	public Phone(String phoneNumber, String phoneType) {
		this.phoneNumber = phoneNumber;
		this.phoneType = phoneType;
	}

	public String getPhoneNumber() {

		return this.phoneNumber;
	}

	public String getPhoneType() {

		return this.phoneType;
	}

}
