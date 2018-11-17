package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

public class MedicationTrackerTest {
	
	MedicationTracker underTest;
	String date;
	
	DateTimeFormatter yyyymmdd = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	
	@Before
	public void setUp() {
		
		date = LocalDateTime.now().format(yyyymmdd);
		
		underTest = new MedicationTracker(1L, 2L);
	}
	
	@Test
	public void shouldHaveMedId() {
		long result = underTest.getMedicationId();
		assertThat(result, is (1L));
	}
	
	@Test
	public void shouldHavePatientId() {
		long result = underTest.getPatientId();
		assertThat(result, is(2L));
	}
	
	@Test
	public void shouldHaveDefaultDoseTakenOfZero() {
		int result = underTest.getDosesTaken();
		assertThat(result, is(0));
	}
	
	
	@Test
	public void shouldHaveDateForTracker() {
		String result = underTest.getDate();
		assertThat(result, is(date));
		
	}
	
}
