package org.wecancodeit.peaceofmind;

//Phone Implements IContactType
public class Phone {

	private String phoneNumber;
	private String type;

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
