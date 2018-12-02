package org.wecancodeit.peaceofmind;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
	
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	@Resource
	MedicationLogRepository medLogRepo;

	@Resource
	MedicationTrackerRepository medTrackerRepo;

	@Resource
	MedicationRepository medRepo;

	DateTimeFormatter yyyymmdd = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	String todayDate = LocalDateTime.now().format(yyyymmdd);
	String pastDate;
	int doseNeeded;
	int dosesMissed;
	int pastDosesTaken;

	@Scheduled(cron = "0 * * ? * *")
	public void updateMedTrackers() {

		Collection<MedicationTracker> medTrackers = medTrackerRepo.findAllByDate(todayDate);

		Collection<MedicationLog> medLogs = medLogRepo.findAllByDateTimeContains(todayDate);

		for (MedicationTracker medTracker : medTrackers) {

			int doseCount = 0;

			for (MedicationLog medLog : medLogs) {

				if (medLog.getMedication().equals(medTracker.getMedication())) {
					doseCount++;

				}

			}
			medTracker.setDosesTaken(doseCount);
			medTrackerRepo.save(medTracker);
		}
	}

	@Scheduled(cron = "0 * * ? * *")
	public void createMedTrackers() {

		Iterable<Medication> medications = medRepo.findAll();

		for (Medication medication : medications) {

			MedicationTracker todayMedTracker = medTrackerRepo.findByMedicationAndDate(medication, todayDate);

			if (todayMedTracker == null) {
				todayMedTracker = medTrackerRepo.save(new MedicationTracker(medication));

				// set dosesNeededForTheDay

				if (medication.getFrequencyTime() == doseFrequencyTimeEnum.Daily) {

					doseNeeded = (medication.getFrequencyAmount() * 1);

				} else if (medication.getFrequencyTime() == doseFrequencyTimeEnum.Weekly) {

					pastDate = LocalDateTime.now().minusDays(7).format(yyyymmdd);

					Collection<MedicationTracker> pastMedTrackers = medTrackerRepo
							.findAllByMedicationAndDateGreaterThan(medication, pastDate);

					for (MedicationTracker medTracker : pastMedTrackers) {
						pastDosesTaken += medTracker.getDosesTaken();
					}

					doseNeeded = medication.getFrequencyAmount() - pastDosesTaken;

				} else if (medication.getFrequencyTime() == doseFrequencyTimeEnum.Monthly) {

					pastDate = LocalDateTime.now().minusMonths(1).format(yyyymmdd);

					Collection<MedicationTracker> pastMedTrackers = medTrackerRepo
							.findAllByMedicationAndDateGreaterThan(medication, pastDate);
					

							pastDosesTaken = 0;
					for (MedicationTracker medTracker : pastMedTrackers) {
						pastDosesTaken += medTracker.getDosesTaken();
						

					}
					doseNeeded = medication.getFrequencyAmount() - pastDosesTaken;

				} else {
					doseNeeded = 0;
				}
				todayMedTracker.setDoseNeeded(doseNeeded);
				medTrackerRepo.save(todayMedTracker);
			}

		}
	}
	
	//"0 0 0 * * ?"

	@Scheduled(cron = "0 * * ? * *")
	public void checkForDosesMissed() {
		
		pastDate = LocalDateTime.now().minusHours(24).format(yyyymmdd);
		
		
		Collection<MedicationTracker> medTrackers = medTrackerRepo.findAllByDate(pastDate);
		
		
		for(MedicationTracker medTracker : medTrackers) {
			
			if(medTracker.getMedication().getFrequencyTime() != doseFrequencyTimeEnum.As_Needed) {
			dosesMissed = medTracker.getDosesNeeded() - medTracker.getDosesTaken();
			} else {
				dosesMissed = 0;
			}
			
			medTracker.setDosesMissed(dosesMissed);
			medTrackerRepo.save(medTracker);
		}
	}
}
