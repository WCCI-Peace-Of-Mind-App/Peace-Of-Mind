package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

public class MedicationTrackerTest {
	
	MedicationTracker underTest;
	Medication testMed;
	String date;
	
	DateTimeFormatter yyyymmdd = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	
	@Before
	public void setUp() {
		
		testMed = new Medication("Chocolate Frog", "1 frog", "oral", 1, doseFrequencyTimeEnum.Daily, "img.jpg", "good spirits");
		
		date = LocalDateTime.now().format(yyyymmdd);
		
		underTest = new MedicationTracker(testMed);
	}
	
	@Test
	public void shouldHaveMed() {
		Medication result = underTest.getMedication();
		assertThat(result, is (testMed));
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