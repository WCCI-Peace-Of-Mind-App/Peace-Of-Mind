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
	private NonMedicalUserRepository nonMedUserRepo;
	
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
		NonMedicalUser nonMedUser = nonMedUserRepo.save(new NonMedicalUser("Albus", "Dumbledore", contact, "colleague", "", "" ));
		Medication testMed = medRepo
				.save(new Medication("Chocolate Frog", "1 frog", "oral", 1, "daily", "img.jpg", "good spirits"));
		Patient testPatient = patientRepo.save(new Patient("Gilderoy", "Lockhart", contact, "date", "dementia", nonMedUser, testMed ));

		MedicationLog medLog = medLogRepo.save(new MedicationLog(testMed));
		underTest = medTrackerRepo.save(new MedicationTracker(testMed));
		medTrackerId = underTest.getId();

		entity.flush();
		entity.clear();
		
	}
	
	@Test
	public void shouldGenerateIdForMedTracker() {
		assertThat(medTrackerId, is(greaterThan(0l)));

	}

	@Test
	public void shouldHaveValidMedicationId() {
		Medication med = underTest.getMedication();

		Optional<Medication> medication = medRepo.findById(med.getId());
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