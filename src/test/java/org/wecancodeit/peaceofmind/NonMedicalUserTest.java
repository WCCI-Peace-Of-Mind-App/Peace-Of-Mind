package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class NonMedicalUserTest {
	
	NonMedicalUser underTest;
	String firstName;
	String lastName;
	String address;
	String contactNumber;
	String workNumber;
	String username;
	String password;
	String relationshipWithPatient;
	
	@Before
	public void setUp() {
		firstName = "Joe";
		lastName = "Bob";
		address = "1123 Sesame Street, NoWhere, OH 43101";
		contactNumber = "614-837-5309";
		workNumber = "555-555-5555";
		username = "Mopamus Wrex";
		password = "Password123";
		relationshipWithPatient = "Father";

		underTest = new NonMedicalUser(firstName, lastName, address, contactNumber, workNumber, username, password, relationshipWithPatient);
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
		String testAddress = underTest.getAddress();
		assertThat(testAddress, is(address));
	}
	
	@Test
	public void shouldHaveContactNumberOf6148375309() {
		String testContactNumber = underTest.getContactInfo();
		assertThat(testContactNumber, is(contactNumber));
	}
	
	@Test
	public void shouldHaveWorkNumberOf5555555555() {
		String testWorkNumber = underTest.getWorkNumber();
		assertThat(testWorkNumber, is(workNumber));
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
		address = "789 Market Square, Dublin, OH 41010";
		contactNumber = "123-456-7890";
		workNumber = "987-654-3210";
		username = "xxxGAMERxxx";
		password = "123Eureka!";
		relationshipWithPatient = "Mother";
		
		underTest = new NonMedicalUser(firstName, lastName, address, contactNumber, workNumber, username, password, relationshipWithPatient);
		String testFirstName = underTest.getFirstName();
		String testLastName = underTest.getLastName();
		String testAddress = underTest.getAddress();
		String testContactNumber = underTest.getContactInfo();
		String testWorkNumber = underTest.getWorkNumber();
		String testUsername = underTest.getUserName();
		String testPassword = underTest.getPassword();
		String testRelationshipWithPatient = underTest.getRelationshipWithPatient();

		assertThat(testFirstName, is(firstName));
		assertThat(testLastName, is(lastName));
		assertThat(testAddress, is(address));
		assertThat(testContactNumber, is(contactNumber));
		assertThat(testWorkNumber, is(workNumber));
		assertThat(testUsername, is(username));
		assertThat(testPassword, is(password));
		assertThat(testRelationshipWithPatient, is(relationshipWithPatient));

	}
	
}
