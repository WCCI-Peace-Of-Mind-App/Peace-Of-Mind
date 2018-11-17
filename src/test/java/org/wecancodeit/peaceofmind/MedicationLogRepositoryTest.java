package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
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
public class MedicationLogRepositoryTest {

	@Resource
	private TestEntityManager entity;

	@Resource
	private MedicationLogRepository medLogRepo;

	@Resource
	private PatientRepository patientRepo;

	@Resource
	private MedicationRepository medRepo;

	@Resource
	private ContactInfoRepository contactRepo;

	MedicationLog underTest;

	long medLogId;
	
	DateTimeFormatter yyyymmddmmhh = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");  

	@Before
	public void setUp() {
		ContactInfo contact = contactRepo.save(new ContactInfo());
		Patient testPatient = patientRepo.save(new Patient("Gilderoy", "Lockhart", contact, "date", "dementia"));
		Medication testMed = medRepo
				.save(new Medication("Chocolate Frog", "1 frog", "oral", 1, "daily", "img.jpg", "good spirits"));

		underTest = medLogRepo.save(new MedicationLog(testMed.getId(), testPatient.getId()));
		medLogId = underTest.getId();

		entity.flush();
		entity.clear();
	}

	@Test
	public void shouldGenerateIdForMedLog() {
		assertThat(medLogId, is(greaterThan(0l)));

	}

	@Test
	public void shouldHaveValidPatientId() {
		Optional<MedicationLog> medLog = medLogRepo.findById(medLogId);
		long patId = medLog.get().getPatientId();

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
	public void shouldHaveDateTimeStamp() {
		String pastDateTime = LocalDateTime.now().minusMinutes(20).format(yyyymmddmmhh);
		
		Optional<MedicationLog> medLog = medLogRepo.findById(medLogId);
		String result = medLog.get().getDateTime();

		assertThat(result, not(pastDateTime));
	}

}
