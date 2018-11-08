package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AddressTest {
	
	ContactInfo testContactInfo = new ContactInfo();
	Address underTest = new Address("streetAddress", null, "city", "state", "zip", "type");
	
	@Test
	public void shouldReturnStreetAddress() {
		String result = underTest.getStreetAddress();
		assertThat(result, is("streetAddress"));
	}
	
	@Test
	public void shouldReturnSecondaryField() {
		String result = underTest.getSecondaryField();
		assertNull(result);
	}
	
	@Test
	public void shouldReturnCity() {
		String result = underTest.getCity();
		assertThat(result, is("city"));
	}
	
	@Test
	public void shouldReturnState() {
		String result = underTest.getState();
		assertThat(result, is("state"));
	}
	
	@Test
	public void shouldReturnZip() {
		String result = underTest.getZipCode();
		assertThat(result, is("zip"));
	}
	
	@Test
	public void shouldReturnType() {
		String result = underTest.getType();
		assertThat(result, is("type"));
	}


}
