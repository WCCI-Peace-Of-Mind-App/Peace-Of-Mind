package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

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
		Address result = underTest.getAddress();
		assertThat(result, is(address));
	}
	
	@Test
	public void shouldRemoveAddressFromContactInfo() {
		underTest.addAddress(address);
		underTest.removeAddress();
		Address result = underTest.getAddress();
		assertNull(result);
	}
	
	@Test
	public void shouldAddPhoneToContactInfo() {
		underTest.addPhone(phone);
		Phone result = underTest.getPhone();
		assertThat(result, is(phone));
	}
	
	@Test
	public void shouldRemovePhoneFromContactInfo() {
		underTest.addPhone(phone);
		underTest.removePhone();
		Phone result = underTest.getPhone();
		assertNull(result);
	}

	@Test
	public void shouldAddEmailToContactInfo() {
		underTest.addEmail(email);
		Email result = underTest.getEmail();
		assertThat(result, is(email));
	}
	
	@Test
	public void shouldRemoveEmailFromContactInfo() {
		underTest.addEmail(email);
		underTest.removeEmail();
		Email result = underTest.getEmail();
		assertNull(result);
	}

}
