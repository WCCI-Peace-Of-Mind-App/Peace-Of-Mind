package org.wecancodeit.peaceofmind;

import java.util.Collection;

public class ContactInfo {

	private Collection<String> addresses;
	private Collection<String> phones;
	private Collection<String> emails;

	public Collection<String> getAddresses() {
		return addresses;
	}

	public Collection<String> getPhones() {
		return phones;
	}
	
	public Collection<String> getEmails() {
		return emails;
	}

	public ContactInfo(Collection<String> addresses, Collection<String>phones, Collection<String> emails) {
		this.addresses = addresses;
		this.phones = phones;
		this.emails = emails;
	}


}
