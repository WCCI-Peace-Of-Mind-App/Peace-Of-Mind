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
	String date = LocalDateTime.now().format(yyyymmdd);

	@Scheduled(cron = "0 * * ? * *")
	public void updateMedTrackers() {

		Collection<MedicationTracker> medTrackers = medTrackerRepo.findAllByDate(date);

		Collection<MedicationLog> medLogs = medLogRepo.findAllByDateTimeContains(date);

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

			MedicationTracker todayMedTracker = medTrackerRepo.findByMedicationAndDate(medication, date);

			if (todayMedTracker == null) {
				todayMedTracker = medTrackerRepo.save(new MedicationTracker(medication));

			}

		}
	}

}
