package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.wecancodeit.peaceofmind.ContactInfo;
import org.wecancodeit.peaceofmind.NonMedicalUser;

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
  PasswordEncoder encoder = new BCryptPasswordEncoder();
  assertTrue(encoder.matches(password, testPassword));
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
  String userRole = "NONMEDICAL";
		
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
PasswordEncoder encoder = new BCryptPasswordEncoder();
assertTrue(encoder.matches(password, testPassword));
		assertThat(testRelationshipWithPatient, is(relationshipWithPatient));
  assertThat(underTest.getRole(), is(userRole));

	}
	
}
