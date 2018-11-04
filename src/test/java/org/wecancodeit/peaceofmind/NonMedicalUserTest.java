package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.wecancodeit.peaceofmind.ContactInfo;
import org.wecancodeit.peaceofmind.NonMedicalUser;

public class NonMedicalUserTest {
	
	Collection<Address> addresses = new ArrayList<>();
	Collection<Phone> phones = new ArrayList<>();
	
	NonMedicalUser underTest;
	String firstName;
	String lastName;
	ContactInfo contactInfo;
	String username;
	String password;
	String relationshipWithPatient;
	
	Address address;
	Phone contactNumber;

	@Before
	public void setUp() {
		firstName = "Joe";
		lastName = "Bob";
		address = new Address();
		contactNumber = new Phone();
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
		Collection<Address> testAddress = underTest.getContactInfo().getAddresses();
		assertThat(testAddress, contains(address));
	}
	
	@Test
	public void shouldHaveContactNumberOf6148375309() {
		Collection<Phone> testContactNumber = underTest.getContactInfo().getPhones();
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
		Address address2 = new Address();
		Phone contactNumber2 = new Phone();
		username = "xxxGAMERxxx";
		password = "123Eureka!";
		relationshipWithPatient = "Mother";
		
		addresses.add(address2);
		phones.add(contactNumber2);
		
		contactInfo = new ContactInfo(addresses, phones, null);
		underTest = new NonMedicalUser(firstName, lastName, contactInfo, username, password, relationshipWithPatient);

		String testFirstName = underTest.getFirstName();
		String testLastName = underTest.getLastName();
		Collection<Address> testAddress = underTest.getContactInfo().getAddresses();
		Collection<Phone> testContactNumber = underTest.getContactInfo().getPhones();
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
