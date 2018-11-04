package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

public class ContactInfoTest {
	
	ContactInfo underTest;
	Collection<String> addresses = new ArrayList<>();
	Collection<String> phones = new ArrayList<>();
	Collection<String> emails = new ArrayList<>();

	String address;
	String phone;
	String email;
	
	@Before
	public void setUp() {
		address = "1123 Sesame Street, NoWhere, OH 43101";
		phone = "123-456-7890";
		email = "ABC@XYZ.com";

		addresses.add(address);
		phones.add(phone);
		emails.add(email);
		underTest = new ContactInfo(addresses, phones, emails);
	}
	
	
	@Test
	public void shouldHaveAddressOf1123SesameStreetNoWhereOH43101() {
		Collection<String> testAddress = underTest.getAddresses();
		assertThat(testAddress, contains(address));
	}
	
	@Test
	public void shouldHavePhoneNumberOf1234567890() {
		Collection<String> testPhone = underTest.getPhones();
		assertThat(testPhone, contains(phone));
	}
	
	@Test
	public void shouldHaveEMailOfABCatXYZ_com() {
		Collection<String> testEmail = underTest.getEmails();
		assertThat(testEmail, contains(email));
	}
	
	@Test
	public void shouldVerify2ndUserHasDifferentInformation() {
		String address2 = "221B Baker Street, London, England 11211";
		String phone2 = "987-654-3210";
		String email2 = "123@wcci.net";
		
		addresses.add(address2);
		phones.add(phone2);
		emails.add(email2);
		
		underTest = new ContactInfo(addresses, phones, emails);
		
		Collection<String> testAddresses = underTest.getAddresses();
		Collection<String> testPhones = underTest.getPhones();
		Collection<String> testEmails = underTest.getEmails();
		assertThat(testAddresses, containsInAnyOrder(address, address2));
		assertThat(testPhones, containsInAnyOrder(phone2, phone));
		assertThat(testEmails, containsInAnyOrder(email, email2));
	}

}
