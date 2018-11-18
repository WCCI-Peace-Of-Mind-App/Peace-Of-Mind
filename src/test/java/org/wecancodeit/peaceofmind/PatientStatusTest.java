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
  public void assertGetDateTimeMatches()
  {
    // assemble
    LocalDateTime dtExpected = LocalDateTime.now();
    PatientStatusEnum statusExpected = PatientStatusEnum.NOTWELL;
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
    PatientStatusEnum statusExpected = PatientStatusEnum.WELL;
    PatientStatus psTested = new PatientStatus(statusExpected, new Patient());
    // action
    // assert
    assertThat(statusExpected, is(psTested.getStatus()));
  }
}
