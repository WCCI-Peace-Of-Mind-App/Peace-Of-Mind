package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PatientStatusEnumTest {

  @Test
  public void assertPatientStatusEnumExtendsJavaLangEnum()
  {
    // assemble
    java.lang.Enum<PatientStatusEnum> patientStatus = PatientStatusEnum.HAPPY;
    // action
    
    // assert
    assertThat(patientStatus, instanceOf(PatientStatusEnum.class));
  }
  
  @Test
  public void assertPatientStatusEnumHasSad()
  {
    // assemble
    java.lang.Enum<PatientStatusEnum> patientStatus = PatientStatusEnum.SAD;
    // action
    // assert
    assertThat(patientStatus, instanceOf(PatientStatusEnum.class));
  }
  
  @Test
  public void shouldBeWELLforwellString() {
	  String happy = "happy";
	  PatientStatusEnum underTest = PatientStatusEnum.valueOf(happy.toUpperCase());
	  assertThat(underTest, is(PatientStatusEnum.HAPPY));
  }
  
  @Test
  public void shouldBeNOTWELLfornotWellString() {
	  String notWell = "confused";
	  String modString = notWell.toUpperCase();
	  PatientStatusEnum underTest = PatientStatusEnum.valueOf(modString);
	  assertThat(underTest, is(PatientStatusEnum.CONFUSED));
  }
  
  
}