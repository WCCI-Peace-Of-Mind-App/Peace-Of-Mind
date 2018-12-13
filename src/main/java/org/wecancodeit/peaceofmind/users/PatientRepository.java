package org.wecancodeit.peaceofmind.users;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.peaceofmind.diary.Diary;

public interface PatientRepository extends CrudRepository<Patient, Long> {

	Patient findByMedicalUserId(long id);

	Patient findByDiary(Diary diary);

	Patient findByUserNameAndPassword(String userName, String password);

}
