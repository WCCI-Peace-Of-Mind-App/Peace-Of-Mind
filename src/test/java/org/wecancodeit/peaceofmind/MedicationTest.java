package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class MedicationTest {
	
	Medication underTest;
	
	@Before
	public void setUp() {
	underTest = new Medication("drug", "2 pills", "oral", 2, "two days", "aaa.jpg", "placebo");	
	}

	@Test
	public void shouldHaveAGenericName() {
		String result = underTest.getGenericName();
		assertThat(result, is("drug"));
	}
	
	@Test
	public void shouldHaveADosage() {
		String result = underTest.getDosage();
		assertThat(result, is("2 pills"));
	}
	
	@Test
	public void shouldHaveAnAdministration() {
		String result = underTest.getAdministration();
		assertThat(result, is("oral"));
	}
	
	@Test
	public void shouldHaveAFrequency() {
		int result1 = underTest.getFrequencyAmount();
		String result2 = underTest.getFrequencyTime();
		
		assertThat(result1, is(2));
		assertThat(result2, is("two days"));
	}
	
	@Test
	public void shouldHaveAPhoto() {
		String result = underTest.getPicture();
		assertThat(result, is("aaa.jpg"));
	}
	
	@Test
	public void shouldHaveAReason() {
		String result = underTest.getReason();
		assertThat(result, is("placebo"));
	}
}
