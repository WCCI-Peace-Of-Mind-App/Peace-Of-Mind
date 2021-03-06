package org.wecancodeit.peaceofmind.users;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.wecancodeit.peaceofmind.contact.ContactInfo;

public class PatientTest {
	
	
	ContactInfo contactInfo = new ContactInfo();
	Patient underTest = new Patient("firstName", "lastName", contactInfo, "01/01/0000", "diagnosis", null, "solarid", "str@ng3W3@th3r");
	
	
	@Test
	public void shouldReturnPatientFirstName() {
		String result = underTest.getFirstName();
		assertThat(result, is("firstName"));
	}
	
	@Test
	public void shouldReturnPatientLastName() {
		String result = underTest.getLastName();
		assertThat(result, is("lastName"));
	}
	
	@Test
	public void shouldReturnPatientContactInfo() {
		ContactInfo result = underTest.getContactInfo();
		assertThat(result, is (contactInfo));
	}
	
	@Test
	public void shouldReturnPatientDOB() {
		String result = underTest.getDateOfBirth();
		assertThat(result, is("01/01/0000"));
	}
	
	@Test
	public void shouldReturnPatientDiagnosis() {
		String result = underTest.getDiagnosis();
		assertThat(result, is("diagnosis"));
	}


}
