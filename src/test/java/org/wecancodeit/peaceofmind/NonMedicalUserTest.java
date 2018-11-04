package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

public class NonMedicalUserTest {
	
	Collection<String> addresses = new ArrayList<>();
	Collection<String> phones = new ArrayList<>();
	
	NonMedicalUser underTest;
	String firstName;
	String lastName;
	ContactInfo contactInfo;
	String username;
	String password;
	String relationshipWithPatient;
	
	String address;
	String contactNumber;

	@Before
	public void setUp() {
		firstName = "Joe";
		lastName = "Bob";
		address = "1123 Sesame Street, NoWhere, OH 43101";
		contactNumber = "614-837-5309";
		username = "Mopamus Wrex";
		password = "Password123";
		relationshipWithPatient = "Father";
		
		addresses.add(address);
		phones.add(contactNumber);
		
		contactInfo = new ContactInfo(addresses, phones, null);

		underTest = new NonMedicalUser(firstName, lastName, contactInfo, username, password, relationshipWithPatient);
	}
	
	@Test
	public void shouldHaveAFirstAndLastNameOfJoe_Bob() {
		String testFirstName = underTest.getFirstName();
		String testLastName = underTest.getLastName();
		assertThat(testFirstName, is(firstName));
		assertThat(testLastName, is(lastName));
	}
	
	
	@Test
	public void testAddressIs1123SesameStreetNoWhereOH43101() {
		Collection<String> testAddress = underTest.getContactInfo().getAddresses();
		assertThat(testAddress, contains(address));
	}
	
	@Test
	public void shouldHaveContactNumberOf6148375309() {
		Collection<String> testContactNumber = underTest.getContactInfo().getPhones();
		assertThat(testContactNumber, contains(contactNumber));
	}
		
	@Test
	public void shouldHaveUserNameOfMopamusWrex() {
		String testUsername = underTest.getUserName();
		assertThat(testUsername, is(username));
	}
	
	@Test
	public void shouldHavePasswordOfPassword123() {
		String testPassword = underTest.getPassword();
		assertThat(testPassword, is(password));
	}
	
	@Test
	public void shouldHaveRelationshipOfFather() {
		String testRelationshipWithPatient = underTest.getRelationshipWithPatient();
		assertThat(testRelationshipWithPatient, is(relationshipWithPatient));
	}

	@Test
	public void test2ndUserWithAllDifferentValues() {
		firstName = "Todd";
		lastName = "Kenzie";
		String address2 = "789 Market Square, Dublin, OH 41010";
		String contactNumber2 = "123-456-7890";
		username = "xxxGAMERxxx";
		password = "123Eureka!";
		relationshipWithPatient = "Mother";
		
		addresses.add(address2);
		phones.add(contactNumber2);
		
		contactInfo = new ContactInfo(addresses, phones, null);
		underTest = new NonMedicalUser(firstName, lastName, contactInfo, username, password, relationshipWithPatient);

		String testFirstName = underTest.getFirstName();
		String testLastName = underTest.getLastName();
		Collection<String> testAddress = underTest.getContactInfo().getAddresses();
		Collection<String> testContactNumber = underTest.getContactInfo().getPhones();
		String testUsername = underTest.getUserName();
		String testPassword = underTest.getPassword();
		String testRelationshipWithPatient = underTest.getRelationshipWithPatient();

		assertThat(testFirstName, is(firstName));
		assertThat(testLastName, is(lastName));
		assertThat(testAddress, containsInAnyOrder(address, address2));
		assertThat(testContactNumber, containsInAnyOrder(contactNumber, contactNumber2));
		assertThat(testUsername, is(username));
		assertThat(testPassword, is(password));
		assertThat(testRelationshipWithPatient, is(relationshipWithPatient));

	}
	
}
