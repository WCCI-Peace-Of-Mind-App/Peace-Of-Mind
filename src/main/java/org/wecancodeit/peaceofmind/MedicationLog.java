package org.wecancodeit.peaceofmind;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MedicationLog {


	@Id
	@GeneratedValue
	private long id;
	private long medicationId;
	private long patientId;
	private String dateTime;

	public long getId() {
		return id;
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
	
	public MedicationLog() {
		
	}

	public MedicationLog(long medicationId, long patientId) {
		DateTimeFormatter yyyymmddhhmm = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		this.medicationId = medicationId;
		this.patientId = patientId;
		this.dateTime = LocalDateTime.now().format(yyyymmddhhmm);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicationLog other = (MedicationLog) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
