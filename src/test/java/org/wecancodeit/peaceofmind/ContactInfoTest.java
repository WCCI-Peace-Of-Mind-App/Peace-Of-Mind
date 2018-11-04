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
	Collection<Address> addresses = new ArrayList<>();
	Collection<Phone> phones = new ArrayList<>();
	Collection<String> emails = new ArrayList<>();

	Address address;
	Phone phone;
	String email;
	
	@Before
	public void setUp() {
		address = new Address();
		phone = new Phone();
		email = "ABC@XYZ.com";

		addresses.add(address);
		phones.add(phone);
		emails.add(email);
		underTest = new ContactInfo(addresses, phones, emails);
	}
	
	
	@Test
	public void shouldHaveAddressOf1123SesameStreetNoWhereOH43101() {
		Collection<Address> testAddress = underTest.getAddresses();
		assertThat(testAddress, contains(address));
	}
	
	@Test
	public void shouldHavePhoneNumberOf1234567890() {
		Collection<Phone> testPhone = underTest.getPhones();
		assertThat(testPhone, contains(phone));
	}
	
//	@Test
//	public void shouldHaveEMailOfABCatXYZ_com() {
//		Collection<String> testEmail = underTest.getEmails();
//		assertThat(testEmail, contains(email));
//	}
	
	@Test
	public void shouldVerify2ndUserHasDifferentInformation() {
		Address address2 = new Address();
		Phone phone2 = new Phone();
		String email2 = "123@wcci.net";
		
		addresses.add(address2);
		phones.add(phone2);
		emails.add(email2);
		
		underTest = new ContactInfo(addresses, phones, emails);
		
		Collection<Address> testAddresses = underTest.getAddresses();
		Collection<Phone> testPhones = underTest.getPhones();
//		Collection<String> testEmails = underTest.getEmails();
		assertThat(testAddresses, containsInAnyOrder(address, address2));
		assertThat(testPhones, containsInAnyOrder(phone2, phone));
//		assertThat(testEmails, containsInAnyOrder(email, email2));
	}

}
