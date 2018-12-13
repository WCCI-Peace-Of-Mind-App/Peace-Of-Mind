package org.wecancodeit.peaceofmind.diary;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.peaceofmind.users.Patient;

public interface DiaryRepository extends CrudRepository<Diary, Long> {

	Collection<Diary> findAllByPatient(Patient patient);

	Collection<Diary> findAllByPatientAndDateTimeContains(Patient patient, String date);

	
}
