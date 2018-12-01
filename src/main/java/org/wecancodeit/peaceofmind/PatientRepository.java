package org.wecancodeit.peaceofmind;

import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Long> {

	Patient findByMedicalUserId(long id);

}
