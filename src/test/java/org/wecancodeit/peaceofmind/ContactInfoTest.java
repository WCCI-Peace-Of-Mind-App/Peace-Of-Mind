package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ContactInfoTest {
	
	ContactInfo underTest;
	String address;
	String phone;
	String email;
	
	@Before
	public void setUp() {
		address = "1123 Sesame Street, NoWhere, OH 43101";
		phone = "123-456-7890";
		email = "ABC@XYZ.com";

		underTest = new ContactInfo(address, phone, email);
	}
	
	
	@Test
	public void shouldHaveAddressOf1123SesameStreetNoWhereOH43101() {
		String testAddress = underTest.getAddress();
		assertThat(testAddress, is(address));
	}
	
	@Test
	public void shouldHavePhoneNumberOf1234567890() {
		String testPhone = underTest.getPhone();
		assertThat(testPhone, is(phone));
	}
	
	@Test
	public void shouldHaveEMailOfABCatXYZ_com() {
		String testEmail = underTest.getEmail();
		assertThat(testEmail, is(email));
	}
	
	
	@Test
	public void shouldVerify2ndUserHasDifferentInformation() {
		address = "221B Baker Street, London, England 11211";
		phone = "987-654-3210";
		email = "123@wcci.net";
		underTest = new ContactInfo(address, phone, email);
		
		String testAddress = underTest.getAddress();
		String testPhone = underTest.getPhone();
		String testEmail = underTest.getEmail();
		assertThat(testAddress, is(address));
		assertThat(testPhone, is(phone));
		assertThat(testEmail, is(email));
	}

}
