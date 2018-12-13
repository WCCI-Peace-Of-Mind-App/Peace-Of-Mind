package org.wecancodeit.peaceofmind.patientStatus;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface PatientStatusRepository extends CrudRepository<PatientStatus, Long> {
	
  Collection<PatientStatus> findByParentIdOrderByStatusDateTimeStampDesc(long id);
  
  PatientStatus findTop1ByParentIdOrderByStatusDateTimeStampDesc(long id);
 
  Collection<PatientStatus> findTop3ByParentIdOrderByStatusDateTimeStampDesc(long id);
  
}
