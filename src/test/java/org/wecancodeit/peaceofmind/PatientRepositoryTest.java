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
import org.wecancodeit.peaceofmind.contact.ContactInfo;
import org.wecancodeit.peaceofmind.contact.ContactInfoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class PatientRepositoryTest {
	
	@Resource
	private TestEntityManager entity;
	
	@Resource
	private PatientRepository patientRepo;
	
	@Resource
	private ContactInfoRepository contactInfoRepo;
	
	@Resource
	private MedicalUserRepository medUserRepo;
	
	ContactInfo contactInfo;
	ContactInfo contactInfo2;
	Patient patient;
	Patient patient2;
	long patientId;
	MedicalUser medUser;
	long medUserId;
	
	@Before
	public void setUp() {
		contactInfo = contactInfoRepo.save(new ContactInfo());
		patient = patientRepo.save(new Patient(null, null, contactInfo, null, null, null, "solarid", "str@ng3W3@th3r"));
		patientId = patient.getId();
		
		contactInfo2 = contactInfoRepo.save(new ContactInfo());
		patient2 = patientRepo.save(new Patient(null, null, contactInfo2, null, null, null, "solarid", "str@ng3W3@th3r"));
		
		medUser = medUserRepo.save(new MedicalUser("Otto", "Octavius", null, "Therapist", "Ohio State Med", "911", "docOc", "tentacles8", patient));
		medUserId = medUser.getId();
		
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
	
	@Test
	public void shouldEstablishPatientToMedUserRelationship() {
		Optional<Patient> underTest = patientRepo.findById(patientId);
		Patient testPatient = underTest.get();
		assertThat(testPatient.getMedicalUser(), is(medUser));

	}
	
	@Test
	public void shouldFindPatientFromMedUserId() {
		Patient underTest = patientRepo.findByMedicalUserId(medUserId);
		assertThat(underTest, is(patient));
	}

}
