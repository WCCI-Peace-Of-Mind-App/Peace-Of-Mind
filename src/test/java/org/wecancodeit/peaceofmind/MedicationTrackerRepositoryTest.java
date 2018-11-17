package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class MedicationTrackerRepositoryTest {

	@Resource
	private TestEntityManager entity;
	
	@Resource 
	private MedicationTrackerRepository medTrackerRepo;
	
	@Resource
	private MedicationLogRepository medLogRepo;
	
	@Resource
	private MedicationRepository medRepo;
	
	@Resource
	private PatientRepository patientRepo;
	
	@Resource
	private ContactInfoRepository contactRepo;
	
	MedicationTracker underTest;
	
	long medTrackerId;
	
	DateTimeFormatter yyyymmdd = DateTimeFormatter.ofPattern("yyyy/MM/dd"); 
	
	@Before
	public void setUp() {
		
		ContactInfo contact = contactRepo.save(new ContactInfo());
		Patient testPatient = patientRepo.save(new Patient("Gilderoy", "Lockhart", contact, "date", "dementia"));
		Medication testMed = medRepo
				.save(new Medication("Chocolate Frog", "1 frog", "oral", 1, "daily", "img.jpg", "good spirits"));

		MedicationLog medLog = medLogRepo.save(new MedicationLog(testMed.getId(), testPatient.getId()));
		underTest = medTrackerRepo.save(new MedicationTracker(testMed.getId(), testPatient.getId()));
		medTrackerId = underTest.getId();
		
		
		
		entity.flush();
		entity.clear();
		
	}
	
	@Test
	public void shouldGenerateIdForMedTracker() {
		assertThat(medTrackerId, is(greaterThan(0l)));

	}

	@Test
	public void shouldHaveValidPatientId() {
		Optional<MedicationTracker> medTracker = medTrackerRepo.findById(medTrackerId);
		long patId = medTracker.get().getPatientId();

		Optional<Patient> patient = patientRepo.findById(patId);
		String result = patient.get().getFirstName();

		assertThat(result, is("Gilderoy"));
	}

	@Test
	public void shouldHaveValidMedicationId() {
		long medId = underTest.getMedicationId();

		Optional<Medication> medication = medRepo.findById(medId);
		String result = medication.get().getGenericName();

		assertThat(result, is("Chocolate Frog"));

	}
	
	@Test
	public void shouldHaveDateStampWithoutTime() {
		String date = LocalDateTime.now().format(yyyymmdd);
		
		Optional<MedicationTracker> medTracker = medTrackerRepo.findById(medTrackerId);
		String result = medTracker.get().getDate();

		assertThat(result, is(date));
	}
	
	
	
}
