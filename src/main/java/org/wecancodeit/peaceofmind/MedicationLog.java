package org.wecancodeit.peaceofmind;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MedicationLog {


	@Id
	@GeneratedValue
	private long id;
	@ManyToOne
	private Medication medication;
	private String dateTime;

	public long getId() {
		return id;
	}

	public Medication getMedication() {
		return medication;
	}
	
	public String getDateTime() {
		return dateTime;
	}
	
	public MedicationLog() {
		
	}
	
	//for testing purposes only//
	public MedicationLog(Medication medication, String pastDateTime) {
		this.medication = medication;
		this.dateTime = pastDateTime;
	}

	public MedicationLog(Medication medication) {
		DateTimeFormatter yyyymmddhhmm = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		this.medication = medication;
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
