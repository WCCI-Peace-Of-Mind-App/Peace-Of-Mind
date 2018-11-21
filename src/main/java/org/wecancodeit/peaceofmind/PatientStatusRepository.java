package org.wecancodeit.peaceofmind;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface PatientStatusRepository extends CrudRepository<PatientStatus, Long> {
	
  Collection<PatientStatus> findByParentIdOrderByStatusDateTimeStampDesc(long id);
  
}
