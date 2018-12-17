package org.wecancodeit.peaceofmind.diary;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wecancodeit.peaceofmind.users.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class DiaryRepositoryTest {

	@Resource
	private TestEntityManager entity;

	@Resource
	private PatientRepository patientRepo;

	@Resource
	private DiaryRepository diaryRepo;

	Diary underTest;
	long diaryId;

	Patient patient;

	DateTimeFormatter yyyymmdd = DateTimeFormatter.ofPattern("yyyy/MM/dd");

	String dateTest;

	@Before
	public void setUp() {
		patient = patientRepo.save(new Patient("firstName", "lastName", null, "01/01/0000", "diagnosis", null, "solarid", "str@ng3W3@th3r"));
		underTest = diaryRepo.save(new Diary("entry text", patient));
		diaryId = underTest.getId();

		dateTest = LocalDateTime.now().format(yyyymmdd);

		entity.flush();
		entity.clear();

	}

	@Test
	public void shouldGenerateIdForDiary() {
		long result = underTest.getId();

		assertNotNull(result);
	}
	
	@Test
	public void shouldFindDiaryById() {
		Diary result = diaryRepo.findById(diaryId).get();
		assertThat(result, is(underTest));
	}
	
	@Test
	public void shouldBeAbleToFindByPatient() {
		Collection<Diary> result = diaryRepo.findAllByPatient(patient);
		assertThat(result, contains(underTest));
	}
	
	@Test
	public void shouldBeAbleToFindPatientForDiary() {
		Patient result = patientRepo.findByDiary(underTest);
		assertThat(result, is(patient));
	}
	
	@Test
	public void shouldBeAbleToSearchForDiariesByDate() {
		Collection<Diary> result = diaryRepo.findAllByPatientAndDateTimeContains(patient, dateTest);
		assertThat(result, contains(underTest));
	}

}
