package org.wecancodeit.peaceofmind;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface MedicationTrackerRepository extends CrudRepository<MedicationTracker, Long> {

	Collection<MedicationTracker> findAllByDate(String date);

}