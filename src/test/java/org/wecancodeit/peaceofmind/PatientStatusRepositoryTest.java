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
public class PatientStatusRepositoryTest {

	
	@Resource
	private TestEntityManager entity;
	
	@Resource
	private PatientStatusRepository patientStatusRepo;
	
	@Resource
	private PatientRepository patientRepo;
	
	private Patient patient1;
	private PatientStatus patientStatus1;
	
	long psId;
	
	@Before
	public void setUp() {
		patient1 = patientRepo.save(new Patient("Joe", "Bob", null, "01/01/2001", "alzh", null));
		
		patientStatus1 = patientStatusRepo.save(new PatientStatus(PatientStatusEnum.HAPPY, patient1));
		psId = patientStatus1.getId();
		
		entity.flush();
		entity.clear();
		
	}
	
	@Test
	public void shouldHaveAnIdGreaterThan0() {
		assertThat(psId, greaterThan(0L));
	}
	
	@Test
	public void shouldSaveAndLoadPatientStatus() {
		Optional<PatientStatus> psTest = patientStatusRepo.findById(psId);
		PatientStatus result = psTest.get();
		assertThat(result, is(patientStatus1));
	}
	
}
