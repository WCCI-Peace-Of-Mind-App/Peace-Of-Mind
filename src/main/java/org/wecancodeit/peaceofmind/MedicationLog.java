package org.wecancodeit.peaceofmind;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MedicationLog {
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");  

	private long medicationId;
	private long patientId;
	private String dateTime;

	public MedicationLog(long medicationId, long patientId) {
		this.medicationId = medicationId;
		this.patientId = patientId;
		this.dateTime = LocalDateTime.now().format(dtf);

	}

	public long getMedicationId() {
		return medicationId;
	}

	public long getPatientId() {
		return patientId;
	}

	public String getDateTime() {
		return dateTime;
	}
	
	

}
