package org.wecancodeit.peaceofmind.patientStatus;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface PatientStatusRepository extends CrudRepository<PatientStatus, Long> {
	
  Collection<PatientStatus> findByPatientIdOrderByStatusDateTimeStampDesc(long id);
  
  PatientStatus findTop1ByPatientIdOrderByStatusDateTimeStampDesc(long id);
 
  Collection<PatientStatus> findTop3ByPatientIdOrderByStatusDateTimeStampDesc(long id);
  
}
