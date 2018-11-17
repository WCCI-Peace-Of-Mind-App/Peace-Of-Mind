package org.wecancodeit.peaceofmind;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PatientStatus implements Iterable<Map<LocalDateTime,PatientStatusEnum>> {
  private Map<LocalDateTime, PatientStatusEnum> psRecord;
  private Object[] dateTimeArray = null;
  public PatientStatus()
  {
    this.psRecord = new HashMap<LocalDateTime, PatientStatusEnum>();
  }

  public void addStatus(PatientStatusEnum expectedStatus)
  {
    this.psRecord.put(LocalDateTime.now(), expectedStatus);
    this.dateTimeArray = this.psRecord.keySet().toArray();
  }

  public PatientStatusEnum getCurrentStatus()
  {
    Object[] allStatus = this.psRecord.values().toArray();
    return (PatientStatusEnum)allStatus[allStatus.length-1];
  }

  public Map<LocalDateTime, PatientStatusEnum> getAllStatus()
  {
    return this.psRecord;
  }

  public Map<LocalDateTime, PatientStatus> getNextTimeStatusReverse()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Iterator<Map<LocalDateTime, PatientStatusEnum>> iterator() {
    Iterator<Map<LocalDateTime, PatientStatusEnum>> answer =
        new Iterator<Map<LocalDateTime, PatientStatusEnum>>() {

          private int currentIndex = 0;

          @Override
          public boolean hasNext()
          {
            return currentIndex > -1 && dateTimeArray[currentIndex] != null;
          }

          @Override
          public Map<LocalDateTime, PatientStatusEnum> next()
          {
            Map<LocalDateTime, PatientStatusEnum> duple = new HashMap();
            duple.put((LocalDateTime)dateTimeArray[currentIndex], psRecord.get(dateTimeArray[currentIndex--]));
            return duple;
          }

          @Override
          public void remove()
          {
            throw new UnsupportedOperationException();
          }
      };
      return answer;
  }

}
