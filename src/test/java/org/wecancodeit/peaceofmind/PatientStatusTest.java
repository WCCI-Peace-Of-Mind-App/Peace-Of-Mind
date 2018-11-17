package org.wecancodeit.peaceofmind;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
//import org.apache.commons.collections4.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class PatientStatusTest {

  @Test
  public void assertGetCurrentStatusReturnsPatientStatusEnum()
  {
    // assemble
    PatientStatus status = new PatientStatus();
    PatientStatusEnum expectedStatus = PatientStatusEnum.WELL; 
    status.addStatus(expectedStatus);
    // action
    java.lang.Enum<PatientStatusEnum> currentStatus = status.getCurrentStatus();
    // assert
    assertThat(currentStatus, is(expectedStatus));
  }
  
  @Test
  public void assertGetCurrentSTatusReturnsPatientStatusEnumNotWell()
  {
    // assemble
    PatientStatus status = new PatientStatus();
    PatientStatusEnum expectedStatus = PatientStatusEnum.NOTWELL; 
    status.addStatus(expectedStatus);
    // action
    java.lang.Enum<PatientStatusEnum> currentStatus = status.getCurrentStatus();
    // assert
    assertThat(currentStatus, is(expectedStatus));
  }
  
  @Test
  public void assertGetAllStatusReturnsAllMockEntries() throws InterruptedException
  {
    // assemble
    PatientStatus status = new PatientStatus();
    PatientStatusEnum firstWell = PatientStatusEnum.WELL;
    PatientStatusEnum secondWell = PatientStatusEnum.WELL;
    PatientStatusEnum firstNotWell = PatientStatusEnum.NOTWELL;
    LocalDateTime expectedToday = LocalDateTime.now();
    // action
    status.addStatus(firstWell);
    TimeUnit.SECONDS.sleep(1);
    status.addStatus(firstNotWell);
    TimeUnit.SECONDS.sleep(1);
    status.addStatus(secondWell);
    // assert
    assertThat(status.getAllStatus().values(), containsInAnyOrder(firstWell, secondWell, firstNotWell));
  }
  
  @Test
  public void assertCanIteratePatientStatusReverseChronological() throws InterruptedException
  {
 // assemble
    PatientStatus status = new PatientStatus();
    PatientStatusEnum firstWell = PatientStatusEnum.WELL;
    PatientStatusEnum secondWell = PatientStatusEnum.WELL;
    PatientStatusEnum firstNotWell = PatientStatusEnum.NOTWELL;
    LocalDateTime expectedToday = LocalDateTime.now();
    // action
    status.addStatus(firstWell);
    TimeUnit.SECONDS.sleep(1);
    status.addStatus(firstNotWell);
    TimeUnit.SECONDS.sleep(1);
    status.addStatus(secondWell);
    // assert
    int i = 0;
    for(Map<LocalDateTime, PatientStatusEnum> aTimeStatusDuple : status){
      if(i==0){ 
        assertThat(aTimeStatusDuple.get(aTimeStatusDuple.keySet().toArray()[0]), is(secondWell));
        assertThat(((LocalDateTime) aTimeStatusDuple.keySet().toArray()[0]).getDayOfYear(), is(expectedToday.getDayOfYear()));
      }
      else if(i==1) assertThat(aTimeStatusDuple, is(firstNotWell));
      else assertThat(aTimeStatusDuple, is(firstWell));
    }
    assertThat(status.getAllStatus().values(), containsInAnyOrder(firstWell, secondWell, firstNotWell));
  }
}
