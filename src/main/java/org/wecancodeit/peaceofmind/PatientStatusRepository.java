package org.wecancodeit.peaceofmind;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface PatientStatusRepository extends CrudRepository<PatientStatus, Long> {
	  
  PatientStatus findTop1ByPatientIdOrderByStatusDateTimeStampDesc(long id);
 
  Collection<PatientStatus> findTop3ByPatientIdOrderByStatusDateTimeStampDesc(long id);
  
  Collection<PatientStatus> findByPatientIdOrderByStatusDateTimeStampDesc(long id);

}
