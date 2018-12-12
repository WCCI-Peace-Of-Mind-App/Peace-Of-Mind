package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class MedicationRepositoryTest {
	
	@Resource
	private TestEntityManager entity;
	
	@Resource
	private MedicationRepository medRepo;
	
	@Resource
	private PatientRepository patientRepo;
	
	Medication med1;
	Medication med2;
	Medication medNot;
	
	long medId;
	long patientId;
	
	Patient patient;
	
	@Before
	public void setUp() {
		med1 = medRepo.save(new Medication("drug", "2 pills", AdministrationEnum.ORAL, 2, doseFrequencyTimeEnum.Daily, "aaa.jpg", "placebo"));
		medId = med1.getId();
		
		med2 = medRepo.save(new Medication("drugs", "1L", AdministrationEnum.ORAL, 1, doseFrequencyTimeEnum.Daily, "bbb.jpg", "memory"));
		
		medNot = medRepo.save(new Medication("blammo", "5 cc", AdministrationEnum.INJECTION, 1, doseFrequencyTimeEnum.Weekly, "xxx.jpg", "nausea"));
		
		patient = patientRepo.save(new Patient("John", "Bowles", null, "01/01/2001", "Alzh", null, "solarid", "str@ng3W3@th3r", med1, med2));
		patientId = patient.getId();
		
		entity.flush();
		entity.clear();
	}
	
	@Test
	public void shouldHaveAnId() {
		assertThat(medId, greaterThan(0L));
	}
	
	@Test
	public void shouldSaveAndLoadMed() {
		Optional<Medication> underTest = medRepo.findById(medId);
		Medication result = underTest.get();
		assertThat(result, is(med1));
	}
	
	@Test
	public void shouldEstablishRelationshipWithPatient() {
		Optional<Patient> result = patientRepo.findById(patientId);
		patient = result.get();
		assertThat(patient.getMedications(),containsInAnyOrder(med2,med1));
	}
	
	

}
