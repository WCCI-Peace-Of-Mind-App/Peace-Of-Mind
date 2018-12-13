package org.wecancodeit.peaceofmind.medication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.scheduling.annotation.EnableScheduling;

@Entity
@EnableScheduling
public class MedicationTracker {

	@Id
	@GeneratedValue
	private long id;
	@ManyToOne
	private Medication medication;
	private int dosesTaken;
	private int dosesNeeded;
	private int dosesMissed;
	private String date;

	public long getId() {
		return id;
	}

	public Medication getMedication() {
		return medication;
	}

	public int getDosesTaken() {
		return dosesTaken;
	}

	public void setDosesTaken(int totalDoses) {
		dosesTaken = totalDoses;
	}
	
	public int getDosesNeeded() {
		return dosesNeeded;
	}
	
	public void setDoseNeeded(int doseNeeded) {
		dosesNeeded = doseNeeded;
		
	}
	
	public int getDosesMissed() {
		return dosesMissed;
	}
	
	public void setDosesMissed(int doseMissed) {
		dosesMissed = doseMissed;
	}

	public String getDate() {
		return date;
	}

	public MedicationTracker() {

	}

	public MedicationTracker(Medication medication) {

		DateTimeFormatter yyyymmdd = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		this.medication = medication;
		this.dosesTaken = 0;
		this.date = LocalDateTime.now().format(yyyymmdd);
	}

	//for testing purposes only
	public MedicationTracker(Medication medication, String pastDate, int needed, int taken) {
		this.medication = medication;
		this.dosesNeeded = needed;
		this.dosesTaken = taken;
		this.date = pastDate;
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
		MedicationTracker other = (MedicationTracker) obj;
		if (id != other.id)
			return false;
		return true;
	}


}