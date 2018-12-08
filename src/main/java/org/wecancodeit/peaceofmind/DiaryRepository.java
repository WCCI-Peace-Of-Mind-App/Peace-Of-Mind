package org.wecancodeit.peaceofmind;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface DiaryRepository extends CrudRepository<Diary, Long> {

	Collection<Diary> findAllByPatient(Patient patient);

	Collection<Diary> findAllByDateTimeContains(String date);

	
}
