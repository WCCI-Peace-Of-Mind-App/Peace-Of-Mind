package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class NonMedicalUserTest {
	
	NonMedicalUser underTest;
	String firstName;
	String lastName;
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void shouldHaveAFirstAndLastName() {
		firstName = "Joe";
		lastName = "Bob";
		underTest = new NonMedicalUser();
		String testFirstName = underTest.getFirstName();
		String testLastName = underTest.getLastName();
		assertThat(testFirstName, is(firstName));
		assertThat(testLastName, is(lastName));
	}

}
