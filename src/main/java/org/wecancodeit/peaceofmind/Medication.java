package org.wecancodeit.peaceofmind;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Medication {

	
	@Id
	@GeneratedValue
	private long id;
	private String genericName;
	private String dosage;
	private String administration;
	private int frequencyAmount;
	private String frequencyTime;
	private String picture;
	private String reason;
	
	@ManyToOne
	private Patient patient;
	


	public long getId() {
		return id;
	}
	
	public String getGenericName() {
		return genericName;
	}

	public String getDosage() {
		return dosage;
	}

	public String getAdministration() {
		return administration;
	}

	public int getFrequencyAmount() {
		return frequencyAmount;
	}
	
	public String getFrequencyTime() {
		return frequencyTime;
	}
	
	public String getPicture() {
		return picture;
	}

	public String getReason() {
		return reason;
	}

	public Patient getPatient() {
		return patient;
	}

	public Medication() {}
	
	public Medication(String genericName, String dosage, String administration, int frequencyAmount, String frequencyTime, String picture, String reason) {
		this.genericName = genericName;
		this.dosage = dosage;
		this.administration = administration;
		this.frequencyAmount = frequencyAmount;
		this.frequencyTime = frequencyTime;
		this.picture = picture;
		this.reason = reason;
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
		Medication other = (Medication) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}

