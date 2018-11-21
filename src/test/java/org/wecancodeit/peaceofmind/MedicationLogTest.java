package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

public class MedicationLogTest {
	
	MedicationLog underTest;
	String date;
	String datetime;
	Medication testMed;
	
	DateTimeFormatter yyyymmddhhmm = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");  
	
	@Before
	public void setUp() {
		underTest = new MedicationLog(testMed);
		
	}
	
	@Test
	public void shouldHaveMed() {
		Medication result = underTest.getMedication();
		assertThat(result, is(testMed));
	}
	
	
	@Test
	public void shouldHaveDateTime() {
		datetime = LocalDateTime.now().format(yyyymmddhhmm);
		String result = underTest.getDateTime();
		assertThat(result, is(datetime));
	}
}
