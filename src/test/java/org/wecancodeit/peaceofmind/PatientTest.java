package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.wecancodeit.peaceofmind.ContactInfo;
import org.wecancodeit.peaceofmind.Patient;

public class PatientTest {
	
	
	ContactInfo contactInfo = new ContactInfo(null, null, null);
	Patient underTest = new Patient("firstName", "lastName", contactInfo, "01/01/0000", "diagnosis");
	
	
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
	public void shouldReturnPatientEmail() {
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
