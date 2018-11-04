package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PatientTest {
	
	Patient underTest = new Patient("firstName", "lastName", "contactinfo", "01/01/0000", "diagnosis");
	
	
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
		String result = underTest.getContactInfo();
		assertThat(result, is ("contactinfo"));
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
