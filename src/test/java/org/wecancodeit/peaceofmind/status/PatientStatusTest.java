package org.wecancodeit.peaceofmind.status;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;
import org.wecancodeit.peaceofmind.users.Patient;

public class PatientStatusTest {

  @Test
  public void assertGetDateTimeMatches()
  {
    // assemble
    LocalDateTime dtExpected = LocalDateTime.now();
    PatientStatusEnum statusExpected = PatientStatusEnum.SAD;
    PatientStatus psTested = new PatientStatus(statusExpected, dtExpected, new Patient());
    // action
    
    // assert
    assertThat(dtExpected, is(psTested.getDateTime()));
    assertThat(statusExpected, is(psTested.getStatus()));
  }
  
  @Test
  public void assertGetWellStatusMatches()
  {
    // assemble
    PatientStatusEnum statusExpected = PatientStatusEnum.HAPPY;
    PatientStatus psTested = new PatientStatus(statusExpected, new Patient());
    // action
    // assert
    assertThat(statusExpected, is(psTested.getStatus()));
  } 
}
