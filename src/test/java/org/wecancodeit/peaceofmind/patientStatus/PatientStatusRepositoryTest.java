package org.wecancodeit.peaceofmind.patientstatus;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wecancodeit.peaceofmind.patientstatus.PatientStatus;
import org.wecancodeit.peaceofmind.patientstatus.PatientStatusEnum;
import org.wecancodeit.peaceofmind.patientstatus.PatientStatusRepository;
import org.wecancodeit.peaceofmind.users.Patient;
import org.wecancodeit.peaceofmind.users.PatientRepository;

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
	
	long patientId;
	long psId;
	
	private PatientStatusEnum happy = PatientStatusEnum.HAPPY;
	private PatientStatusEnum sad = PatientStatusEnum.SAD;
	private PatientStatusEnum angry = PatientStatusEnum.ANGRY;
	private PatientStatusEnum confused = PatientStatusEnum.CONFUSED;
	
	private LocalDateTime time1 = LocalDateTime.of(1980, 1, 5, 8, 30);
	private LocalDateTime time2 = LocalDateTime.of(1980, 2, 5, 8, 30);
	private LocalDateTime time3 = LocalDateTime.of(1980, 2, 6, 8, 30);
	private LocalDateTime time4 = LocalDateTime.of(1980, 2, 6, 9, 30);
	private LocalDateTime time5 = LocalDateTime.of(1980, 2, 6, 9, 31);
	
	private PatientStatus status1;
	private PatientStatus status2;
	private PatientStatus status3;
	private PatientStatus status4;
	private PatientStatus status5; 
	
	@Before
	public void setUp() {
		patient1 = patientRepo.save(new Patient("Joe", "Bob", null, "01/01/2001", "alzh", null, "solarid", "str@ng3W3@th3r"));
		patientId = patient1.getId();
		
		status1 = patientStatusRepo.save(new PatientStatus(happy, time1, patient1));
		status2 = patientStatusRepo.save(new PatientStatus(sad, time2, patient1));
		status3 = patientStatusRepo.save(new PatientStatus(angry, time3, patient1));
		status4 = patientStatusRepo.save(new PatientStatus(confused, time4, patient1));
		status5 = patientStatusRepo.save(new PatientStatus(happy, time5, patient1));		
		psId = status1.getId();
		
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
		assertThat(result, is(status1));
	}
	
	@Test
	public void shouldPullCollectionOfByDateTime() {
		
		Collection<PatientStatus> patientStatuses = patientStatusRepo.findByPatientIdOrderByStatusDateTimeStampDesc(patientId);
		assertThat(patientStatuses, contains(status5, status4, status3, status2, status1));
		
	}
	
	@Test
	public void shouldReturnRecentResult() {
		PatientStatus checkedStatus= patientStatusRepo.findTop1ByPatientIdOrderByStatusDateTimeStampDesc(patientId);
		assertThat(checkedStatus, is(status5));
	}
	
	@Test
	public void shouldReturnTop3RecentStatuses() {
		Collection<PatientStatus> checkedStatuses = patientStatusRepo.findTop3ByPatientIdOrderByStatusDateTimeStampDesc(patientId);
		assertThat(checkedStatuses, contains(status5, status4, status3));
	}
	
}
