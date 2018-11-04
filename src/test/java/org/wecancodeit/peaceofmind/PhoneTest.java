package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PhoneTest {
	
	//Phone Implements IContactType
	
	Phone phoneNum = new Phone("6142002222","Mobile");
	
	@Test
	public void shouldReturnPhoneNumber6142002222() {
		
		String expectedPhoneNumber = "6142002222";
		String actualPhoneNumber = phoneNum.getPhoneNumber();
		assertThat(actualPhoneNumber, is(expectedPhoneNumber));
		
	}
	
	@Test
	public void shouldReturnPhoneNumberTypeMobile() {
		
		String expectedPhoneType = "Mobile";
		String actualPhoneType = phoneNum.getPhoneType();
		assertThat(actualPhoneType, is(expectedPhoneType));
		
	}
	
	@Test
	public void secondPhoneTestObjectShouldReturnValues() {
		
		String expectedPhoneNum2 = "6142002229";
	    String expectedPhoneType2 = "Home";
	    Phone phoneNum2 = new Phone(expectedPhoneNum2,expectedPhoneType2);
		String actualPhoneNum2 = phoneNum2.getPhoneNumber();
		assertThat(actualPhoneNum2, is(expectedPhoneNum2));	
		String actualPhoneTypeHome = phoneNum2.getPhoneType();
		assertThat(actualPhoneTypeHome, is(expectedPhoneType2));	
	}
}
