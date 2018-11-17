package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
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
	
	Patient patient;
	
	@Before
	public void setUp() {
		med1 = medRepo.save(new Medication("drug", "2 pills", "oral", 2, "two days", "aaa.jpg", "placebo"));
		medId = med1.getId();
		
		med2 = medRepo.save(new Medication("drugs", "1L", "oral", 1, "daily", "bbb.jpg", "memory"));
		medNot = medRepo.save(new Medication("blammo", "5 cc", "injection", 1, "weekly", "xxx.jpg", "nausea"));
		
		patient = patientRepo.save(new Patient("John", "Bowles", null, "01/01/2001", "Alzh", null));
		
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
		
		
		
	}
	
	

}
