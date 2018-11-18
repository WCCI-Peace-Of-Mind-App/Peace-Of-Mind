package org.wecancodeit.peaceofmind;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class MedicationTracker {
	
	@Id
	@GeneratedValue
	private long id;
	private long medicationId;
	private long patientId;
	private int dosesTaken;
	private String date;

	public long getId() {
		return id;
	}
	public long getMedicationId() {
		return medicationId;
	}

	public long getPatientId() {
		return patientId;
	}

	public int getDosesTaken() {
		return dosesTaken;
	}

	public void doseTaken() {
		dosesTaken++;
	}

	public String getDate() {
		return date;
	}
	
	public MedicationTracker() {
		
	}

	public MedicationTracker(long medicationId, long patientId) {

		DateTimeFormatter yyyymmdd = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		this.medicationId = medicationId;
		this.patientId = patientId;
		this.dosesTaken = 0;
		this.date = LocalDateTime.now().format(yyyymmdd);
	}


	
}
