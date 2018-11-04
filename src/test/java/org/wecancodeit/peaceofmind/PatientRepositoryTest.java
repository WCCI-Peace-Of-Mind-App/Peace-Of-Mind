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
public class PatientRepositoryTest {
	
	@Resource
	private TestEntityManager entity;
	
	@Resource
	private PatientRepository patientRepo;
	
	@Resource
	private ContactInfoRepository contactInfoRepo;
	
	ContactInfo contactInfo;
	ContactInfo contactInfo2;
	Patient patient;
	Patient patient2;
	long patientId;
	
	@Before
	public void setUp() {
		contactInfo = new ContactInfo(null, null, null);
		patient = patientRepo.save(new Patient(null, null, contactInfo, null, null));
		patientId = patient.getId();
		
		contactInfo2 = new ContactInfo(null, null, null);
		patient2 = patientRepo.save(new Patient(null, null, contactInfo2, null, null));
		
		entity.flush();
		entity.clear();
		
	}
	
	@Test
	public void shouldVerifyPatientHasId() {
		assertThat(patientId, greaterThan(0L));
	}
	
	@Test
	public void shouldSaveAndLoadPatient() {	
		Optional<Patient> underTest = patientRepo.findById(patientId);
		Patient testPatient = underTest.get();
		assertThat(testPatient, is(patient));
	}
	
	@Test
	public void shouldEstablishPatientToContactInfoRelationship() {
		Optional<Patient> underTest = patientRepo.findById(patientId);
		Patient testPatient = underTest.get();
		assertThat(testPatient.getContactInfo(), is(contactInfo));
	}
	

}
