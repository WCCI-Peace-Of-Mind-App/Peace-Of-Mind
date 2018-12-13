package org.wecancodeit.peaceofmind.medication;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wecancodeit.peaceofmind.PatientRepository;
import org.wecancodeit.peaceofmind.contact.ContactInfoRepository;

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
	DateTimeFormatter yyyymmdd = DateTimeFormatter.ofPattern("yyyy/MM/dd"); 	


	@Before
	public void setUp() {
		Medication testMed = medRepo
				.save(new Medication("Chocolate Frog", "1 frog", AdministrationEnum.ORAL, 1, doseFrequencyTimeEnum.Daily, "img.jpg", "good spirits"));

		underTest = medLogRepo.save(new MedicationLog(testMed));
		medLogId = underTest.getId();

		entity.flush();
		entity.clear();
	}

	@Test
	public void shouldGenerateIdForMedLog() {
		assertThat(medLogId, is(greaterThan(0l)));

	}

	@Test
	public void shouldHaveValidMedication() {
		Medication med = underTest.getMedication();

		String result = med.getGenericName();

		assertThat(result, is("Chocolate Frog"));

	}
	

	@Test
	public void shouldHaveDateTimeStamp() {
		String pastDateTime = LocalDateTime.now().minusMinutes(20).format(yyyymmddmmhh);
		
		Optional<MedicationLog> medLog = medLogRepo.findById(medLogId);
		String result = medLog.get().getDateTime();

		assertThat(result, not(pastDateTime));
	}
	
	
	@Test
	public void shouldBeAbleToFindByDate() {
		String date = LocalDateTime.now().format(yyyymmdd);
		
		Collection<MedicationLog> medLogs = medLogRepo.findAllByDateTimeContains(date);
		
		assertThat(medLogs, contains(underTest));
	}
	
	
	
	

}
