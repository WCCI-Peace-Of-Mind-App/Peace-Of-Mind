package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

public class ContactInfoTest {
	
	ContactInfo underTest;

	Address address;
	Phone phone;
	Email email;
	
	@Before
	public void setUp() {
		address = new Address();
		phone = new Phone();
		email = new Email();

		underTest = new ContactInfo();
	}
	
	
	@Test
	public void shouldAddAddressToContactInfo() {
		underTest.addAddress(address);
		Collection<Address> result = underTest.getAddresses();
		assertThat(result, contains(address));
	}
	
	@Test
	public void shouldRemoveAddressFromContactInfo() {
		underTest.addAddress(address);
		underTest.removeAddress(address);
		Collection<Address> result = underTest.getAddresses();
		assertThat(result, not(contains(address)));
	}
	
	@Test
	public void shouldAddPhoneToContactInfo() {
		underTest.addPhone(phone);
		Collection<Phone> result = underTest.getPhones();
		assertThat(result, contains(phone));
	}
	
	@Test
	public void shouldRemovePhoneFromContactInfo() {
		underTest.addPhone(phone);
		underTest.removePhone(phone);
		Collection<Phone> result = underTest.getPhones();
		assertThat(result, not(contains(phone)));
	}

	@Test
	public void shouldAddEmailToContactInfo() {
		underTest.addEmail(email);
		Collection<Email> result = underTest.getEmails();
		assertThat(result, contains(email));
	}
	
	@Test
	public void shouldRemoveEmailFromContactInfo() {
		underTest.addEmail(email);
		underTest.removeEmail(email);
		Collection<Email> result = underTest.getEmails();
		assertThat(result, not(contains(email)));
	}

}
