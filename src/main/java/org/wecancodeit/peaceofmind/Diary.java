package org.wecancodeit.peaceofmind;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Diary {
	
	@Id
	@GeneratedValue
	private long id;
	private String dateTime;
	private String entryText;
	
	@ManyToOne 
	private Patient patient;
	
	public long getId() {
		return id;
	}
	
	public String getDateTime() {
		return dateTime;
	}
	
	public String getEntryText() {
		return entryText;
	}
	
	public Patient getPatient() {
		return patient; 
	}
	
	public Diary() {
		
	}

	public Diary(String entryText, Patient patient) {
		DateTimeFormatter yyyymmddhhmm = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		this.dateTime = LocalDateTime.now().format(yyyymmddhhmm);
		this.entryText = entryText;
		this.patient = patient; 
	}
	
	//for Populator Purposes Only
	public Diary(String pastDateTime, String entryText, Patient patient) {
		this.dateTime = pastDateTime;
		this.entryText = entryText;
		this.patient = patient; 
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
		Diary other = (Diary) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}
