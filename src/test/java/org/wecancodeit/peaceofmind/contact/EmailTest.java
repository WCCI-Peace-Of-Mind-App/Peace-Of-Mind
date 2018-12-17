package org.wecancodeit.peaceofmind.contact;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class EmailTest {
	
	ContactInfo testContactInfo = new ContactInfo();
	Email underTest = new Email("email address", "type");
	
	@Test
	public void shouldReturnEmailAddress() {
	String result = underTest.getEmailAddress();
	assertThat(result, is("email address"));
	}
	
	@Test
	public void shouldReturnType() {
	String result = underTest.getType();
	assertThat(result, is("type"));
	}

}
