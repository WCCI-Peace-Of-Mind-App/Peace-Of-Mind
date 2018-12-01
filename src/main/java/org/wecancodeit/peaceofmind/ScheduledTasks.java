package org.wecancodeit.peaceofmind;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	@Resource
	MedicationLogRepository medLogRepo;

	@Resource
	MedicationTrackerRepository medTrackerRepo;

	@Scheduled(cron = "0 * * ? * *")
	public void updateMedTracker() {

		DateTimeFormatter yyyymmdd = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String date = LocalDateTime.now().format(yyyymmdd);

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

}
