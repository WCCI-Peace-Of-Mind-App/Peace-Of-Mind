package org.wecancodeit.peaceofmind.medication;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface MedicationTrackerRepository extends CrudRepository<MedicationTracker, Long> {

	MedicationTracker findByMedicationAndDate(Medication medication, String date);

	Collection<MedicationTracker> findAllByMedicationAndDateGreaterThan(Medication medication, String date);

	Collection<MedicationTracker> findAllByDate(String date);

	Collection<MedicationTracker> findAllByMedicationAndDateGreaterThanEqual(Medication medication,
			String date);



}