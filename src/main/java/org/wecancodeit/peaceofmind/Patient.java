package org.wecancodeit.peaceofmind;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Patient implements IPerson {
	
	@Id
	@GeneratedValue
	private long id;

	private String firstName;
	private String lastName;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	private ContactInfo contactInfo; 
	private String dateOfBirth;
	private String diagnosis;
	
	@OneToOne
	private NonMedicalUser nonMedicalUser;

	@OneToOne(mappedBy = "patient", fetch = FetchType.LAZY, optional = false)
	private MedicalUser medicalUser;

	@OneToMany
	private Collection<Medication> medications;

	@OneToMany(mappedBy="parent")
	private Collection<PatientStatus> statusHistory;

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
	public ContactInfo getContactInfo() {
		return contactInfo;
	}
	
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

	public Patient() {}
	
	public Patient(String firstName, String lastName, ContactInfo contactInfo, String dateOfBirth, String diagnosis, NonMedicalUser nonMedicalUser, Medication...medications) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactInfo = contactInfo;
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
