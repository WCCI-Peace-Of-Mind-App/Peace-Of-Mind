package org.wecancodeit.peaceofmind.users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.wecancodeit.peaceofmind.contact.ContactInfo;
import org.wecancodeit.peaceofmind.diary.Diary;
import org.wecancodeit.peaceofmind.medication.Medication;
import org.wecancodeit.peaceofmind.status.PatientStatus;

@Entity
public class Patient extends AppUser {
	
	private String dateOfBirth;
	private String diagnosis;
	
	@OneToOne
	private NonMedicalUser nonMedicalUser;

	@OneToOne(mappedBy = "patient", fetch = FetchType.LAZY, optional = false)
	private MedicalUser medicalUser;

	@OneToMany
	private Collection<Medication> medications;

	@OneToMany(mappedBy="patient")
	private Collection<PatientStatus> statusHistory;
	
	@OneToMany(mappedBy="patient")
	private Collection<Diary> diary; 

	public String getDateOfBirth() {
		return dateOfBirth;
	}
	
	public String getDiagnosis() {
		return diagnosis;
	}
	
	public NonMedicalUser getNonMedicalUser() {
		return nonMedicalUser;
	}

	public MedicalUser getMedicalUser() {
		return medicalUser;
	}
	
	public Collection<Medication> getMedications() {
		return medications;
	}
	
	public Collection<PatientStatus> getStatusHistory() {
		return statusHistory;
	}
	
	public Collection<Diary> getDiary() {
		return diary; 
	}
	
	public Patient() {}
	
	public Patient(String firstName, String lastName, ContactInfo contactInfo, String dateOfBirth, String diagnosis, NonMedicalUser nonMedicalUser, String userName, String password, Medication...medications) {
		super(firstName, lastName, userName, password, contactInfo);
		this.dateOfBirth = dateOfBirth;
		this.diagnosis = diagnosis;
		this.nonMedicalUser = nonMedicalUser;
		this.medications = new HashSet<>(Arrays.asList(medications));
		this.statusHistory = new ArrayList<>();
	}
	
	public void addMedication(Medication medication) {
		medications.add(medication);
	}
	
	public void setCurrentStatus(PatientStatus currentStatus) {
		this.statusHistory.add(currentStatus);
	}
	
	public PatientStatus getCurrentStatus() {
		return (PatientStatus)this.statusHistory.toArray()[this.statusHistory.toArray().length-1];
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
		Patient other = (Patient) obj;
		if (id != other.id)
			return false;
		return true;
	}



}
