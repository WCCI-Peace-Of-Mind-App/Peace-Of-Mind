package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PatientStatusEnumTest {

  @Test
  public void assertPatientStatusEnumExtendsJavaLangEnum()
  {
    // assemble
    java.lang.Enum<PatientStatusEnum> patientStatus = PatientStatusEnum.WELL;
    // action
    
    // assert
    assertThat(patientStatus, instanceOf(PatientStatusEnum.class));
  }
  
  @Test
  public void assertPatientStatusEnumHasNotWell()
  {
    // assemble
    java.lang.Enum<PatientStatusEnum> patientStatus = PatientStatusEnum.NOTWELL;
    // action
    // assert
    assertThat(patientStatus, instanceOf(PatientStatusEnum.class));
  }
}