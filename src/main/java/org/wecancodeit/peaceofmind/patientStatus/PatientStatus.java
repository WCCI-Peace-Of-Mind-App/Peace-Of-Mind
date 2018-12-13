package org.wecancodeit.peaceofmind.patientstatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.wecancodeit.peaceofmind.users.Patient;
@Entity
public class PatientStatus {

	@GeneratedValue
	@Id
	private long id;
	private LocalDateTime statusDateTimeStamp = null;
	private PatientStatusEnum patientStatus = null;
	@ManyToOne
	private Patient patient = null;
	
	public long getId() {
		return id;
	}
	
	public PatientStatus() {}
	
	public PatientStatus(PatientStatusEnum currentStatus, LocalDateTime dateTime, Patient parentPatient) {
		this.patientStatus = currentStatus;
		this.statusDateTimeStamp = dateTime;
		this.patient = parentPatient;
	}
	
	public PatientStatus(PatientStatusEnum currentStatus, Patient patient) {
		this.patientStatus = currentStatus;
		this.statusDateTimeStamp = LocalDateTime.now();
		this.patient = patient;
	}
	
	public PatientStatusEnum getStatus() {
		return this.patientStatus;
	}

	public LocalDateTime getDateTime() {
		return this.statusDateTimeStamp;
	}
	
	public String printDateTime() {
		DateTimeFormatter yyyymmddhhmm = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		return statusDateTimeStamp.format(yyyymmddhhmm);
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
		PatientStatus other = (PatientStatus) obj;
		if (id != other.id)
			return false;
		return true;
	}


}
