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
	
	@Before
	public void setUp() {
		firstName = "Joe";
		lastName = "Bob";
		address = "1123 Sesame Street, NoWhere, OH 43101";
		contactNumber = "614-837-5309";
		workNumber = "555-555-5555";
		username = "Mopamus Wrex";
		password = "Password123";

		underTest = new NonMedicalUser(firstName, lastName, address, contactNumber, workNumber, username, password);
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
		String testContactNumber = underTest.getContactNumber();
		assertThat(testContactNumber, is(contactNumber));
	}
	
	@Test
	public void shouldHaveWorkNumberOf5555555555() {
		String testWorkNumber = underTest.getWorkNumber();
		assertThat(testWorkNumber, is(workNumber));
	}
	
	@Test
	public void shouldHaveUserNameOfMopamusWrex() {
		String testUsername = underTest.getUsername();
		assertThat(testUsername, is(username));
	}
	
	@Test
	public void shouldHavePasswordOfPassword123() {
		String testPassword = underTest.getPassword();
		assertThat(testPassword, is(password));
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
		
		underTest = new NonMedicalUser(firstName, lastName, address, contactNumber, workNumber, username, password);
		String testFirstName = underTest.getFirstName();
		String testLastName = underTest.getLastName();
		assertThat(testFirstName, is(firstName));
		assertThat(testLastName, is(lastName));
	}
	
}
