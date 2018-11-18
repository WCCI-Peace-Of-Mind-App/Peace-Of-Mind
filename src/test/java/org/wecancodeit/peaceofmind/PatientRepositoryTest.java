package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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

  @Resource
  private PatientStatusRepository patientStatusRepo;
	
	ContactInfo contactInfo;
	ContactInfo contactInfo2;
	Patient patient;
	Patient patient2;
  PatientStatus patientStatus1;
  PatientStatus patientStatus2;
	long patientId;
	
	@Before
	public void setUp() {
		contactInfo = contactInfoRepo.save(new ContactInfo());
		patient = patientRepo.save(new Patient(null, null, contactInfo, null, null));
		patientId = patient.getId();
		
		contactInfo2 = contactInfoRepo.save(new ContactInfo());
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
	
  @Test
  public void assertGetCurrentStatusReturnsMostRecentDuple() throws InterruptedException
  {
    // assemble
    LocalDateTime firstDT = LocalDateTime.now();
    PatientStatusEnum firstStatus = PatientStatusEnum.WELL;
    patient.setCurrentStatus(patientStatusRepo.save(new PatientStatus(firstStatus,firstDT, patient)));
    TimeUnit.SECONDS.sleep(1);
    LocalDateTime secondDT = LocalDateTime.now();
    PatientStatusEnum secondStatus = PatientStatusEnum.NOTWELL;
    patientStatus2 = patientStatusRepo.save(new PatientStatus(secondStatus,secondDT, patient));
    patient.setCurrentStatus(patientStatus2);
    patient = patientRepo.save(patient);
    // action
    entity.flush();
    entity.clear();
    Optional<Patient> queriedPatient = patientRepo.findById(patientId);
    Patient testPatient = queriedPatient.get();
    // assert
    assertThat(testPatient.getCurrentStatus(), is(patientStatus2));
    assertThat(testPatient.getCurrentStatus().getDateTime(), is(secondDT));
    assertThat(testPatient.getCurrentStatus().getStatus(), is(secondStatus));
  }
  
  @Test
  public void assertGetAllStatusReturnInReverseTimeOrder() throws InterruptedException
  {
 // assemble
    LocalDateTime firstDT = LocalDateTime.now();
    PatientStatusEnum firstStatus = PatientStatusEnum.WELL;
    patient.setCurrentStatus(patientStatusRepo.save(new PatientStatus(firstStatus,firstDT, patient)));
    patientStatus1 = patient.getCurrentStatus();
    TimeUnit.SECONDS.sleep(1);
    LocalDateTime secondDT = LocalDateTime.now();
    PatientStatusEnum secondStatus = PatientStatusEnum.NOTWELL;
    patientStatus2 = patientStatusRepo.save(new PatientStatus(secondStatus,secondDT, patient));
    patient.setCurrentStatus(patientStatus2);
    patient = patientRepo.save(patient);
    // action
    entity.flush();
    entity.clear();
    Optional<Patient> queriedPatient = patientRepo.findById(patientId);
    Collection<PatientStatus> statusOrdered = patientStatusRepo.findByParentIdOrderByStatusDateTimeStampDesc(queriedPatient.get().getId());
    // assert
    assertThat(statusOrdered.toArray()[0], is(patientStatus2));
    assertThat(statusOrdered.toArray()[1], is(patientStatus1));
  }
}
