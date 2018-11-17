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
	
	DateTimeFormatter yyyymmddhhmm = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");  
	
	@Before
	public void setUp() {
		underTest = new MedicationLog(1L, 2L);
		
	}
	
	@Test
	public void shouldHaveMedId() {
		long result = underTest.getMedicationId();
		assertThat(result, is(1L));
	}
	
	@Test
	public void shouldHavePatientId() {
		long result = underTest.getPatientId();
		assertThat(result, is(2L));
	}
	
	@Test
	public void shouldHaveDateTime() {
		date = LocalDateTime.now().format(yyyymmddhhmm);
		String result = underTest.getDateTime();
		assertThat(result, is(date));
	}
}
