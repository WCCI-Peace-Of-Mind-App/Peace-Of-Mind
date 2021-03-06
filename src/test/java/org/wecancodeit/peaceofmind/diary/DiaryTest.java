package org.wecancodeit.peaceofmind.diary;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.wecancodeit.peaceofmind.users.Patient;

public class DiaryTest {

	Patient patient = new Patient("firstName", "lastName", null, "01/01/0000", "diagnosis", null, "solarid", "str@ng3W3@th3r");
	Diary underTest = new Diary("entry text", patient);
	
	DateTimeFormatter yyyymmddhhmm = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
	String dateTimeTest = LocalDateTime.now().format(yyyymmddhhmm);

	
	@Test
	public void shouldReturnTodaysDate() {
		String result = underTest.getDateTime();
		assertThat(result, is(dateTimeTest));
	}
	
	@Test
	public void shouldContainEntryText() {
		String result = underTest.getEntryText();
		assertThat(result, is("entry text"));
	}
	
	
	@Test
	public void shouldHaveAnAssociatedPatient() {
		Patient result = underTest.getPatient();
		assertThat(result, is(patient));
	}

}
