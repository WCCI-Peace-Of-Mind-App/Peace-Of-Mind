package org.wecancodeit.peaceofmind.users;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.wecancodeit.peaceofmind.contact.ContactInfo;

public class NonMedicalUserTest {
	
	NonMedicalUser underTest;
	String firstName;
	String lastName;
	ContactInfo contactInfo;
	String username;
	String password;
	String relationshipWithPatient;
	
	@Before
	public void setUp() {
		firstName = "Joe";
		lastName = "Bob";
		username = "Mopamus Wrex";
		password = "Password123";
		relationshipWithPatient = "Father";
		
		contactInfo = new ContactInfo();

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
		username = "xxxGAMERxxx";
		password = "123Eureka!";
		relationshipWithPatient = "Mother";
		
		contactInfo = new ContactInfo();
		underTest = new NonMedicalUser(firstName, lastName, contactInfo, username, password, relationshipWithPatient);

		String testFirstName = underTest.getFirstName();
		String testLastName = underTest.getLastName();
		String testUsername = underTest.getUserName();
		String testPassword = underTest.getPassword();
		String testRelationshipWithPatient = underTest.getRelationshipWithPatient();

		assertThat(testFirstName, is(firstName));
		assertThat(testLastName, is(lastName));
		assertThat(testUsername, is(username));
		assertThat(testPassword, is(password));
		assertThat(testRelationshipWithPatient, is(relationshipWithPatient));

	}
	
}
