package org.wecancodeit.peaceofmind;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class PatientStatus {
  @GeneratedValue
  @Id
  private Long id;
  private LocalDateTime statusDateTimeStamp = null;
  private PatientStatusEnum patientStatus = null;
  @ManyToOne
  private Patient parent = null;
  public PatientStatus()
  {
  }

  public PatientStatus(PatientStatusEnum currentStatus, LocalDateTime dateTime, Patient parentPatient)
  {
    this.patientStatus = currentStatus;
    this.statusDateTimeStamp = dateTime;
    this.parent = parentPatient;
  }
  
  public PatientStatus(PatientStatusEnum currentStatus, Patient patient)
  {
    this.patientStatus = currentStatus;
    this.statusDateTimeStamp = LocalDateTime.now();
    this.parent = patient;
  }

  public LocalDateTime getDateTime()
  {
    return this.statusDateTimeStamp;
  }

  public PatientStatusEnum getStatus()
  {
    return this.patientStatus;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    PatientStatus other = (PatientStatus) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }
}
