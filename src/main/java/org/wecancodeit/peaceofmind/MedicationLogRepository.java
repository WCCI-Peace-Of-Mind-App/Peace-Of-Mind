package org.wecancodeit.peaceofmind;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface MedicationLogRepository  extends CrudRepository<MedicationLog, Long>{

	Collection<MedicationLog> findAllByDateTimeContains(String date);

}
