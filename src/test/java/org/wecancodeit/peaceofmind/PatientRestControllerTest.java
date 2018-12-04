package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;
@RunWith(SpringRunner.class)
@WebMvcTest(PatientRestController.class)
public class PatientRestControllerTest {
  
  /*
   * @RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class MedicationLogRepositoryTest {

@Resource
private TestEntityManager entity;
   */
  @Resource
  private MockMvc mvc;
  @MockBean
  private PatientRepository patientRepo;
  @MockBean
  private MedicationRepository medicationRepo;
  @MockBean
  private MedicationLogRepository medLogRepo;
  @Mock
  private Patient oMockPatient;
  @Mock
  private Patient oAnotherMockPatient;
  @Mock
  private Medication oMockMedication;
  @Mock
  private Medication oAnotherMockMedication;
  
  @Test
  public void assertPatientLogMedicationConsumption_returnPartial() throws Exception
  {
    // arrange
    // Scenario: patientId and medicationId are passed in POST object.
    // After confirming relevant Patient(.getMedications()) and Medication exist, save said Medication to MedicationLogRepository
    Long patientId = 1234L;
    Long medicationId = 4321L;
    when(patientRepo.findById(new Long(patientId))).thenReturn(Optional.ofNullable(oMockPatient));
    Collection<Medication> oMedicationCollection = new ArrayList();
    oMedicationCollection.add(oMockMedication);
    oMedicationCollection.add(oAnotherMockMedication);
    when(oMockPatient.getMedications()).thenReturn(oMedicationCollection);
    when(oMockMedication.getId()).thenReturn(new Long(medicationId));
    when(medicationRepo.findById(new Long(medicationId))).thenReturn(Optional.ofNullable(oMockMedication));
    // action
    MedicationLog oSavedLog = medLogRepo.save(new MedicationLog(oMockMedication));
    // assert
    mvc.perform(// /show-patients/{patientId}/patient-medication-log-update
        post("/show-patients/1234/patient-medication-log-update").param("medicationId", "4321")
      ).andExpect(status().isOk()
          ).andExpect(view().name(is("partials/patient-medication-log-update.html")))
      .andExpect(content().contentType("text/html;charset=UTF-8"));
//    assertThat(oSavedLog.getMedication(), is(oMockMedication));
//    assertThat(oSavedLog.getId(), is(greaterThan(0L)));
  }
  
  @Test(expected = NestedServletException.class)//PatientNotFoundException.class
  public void assertPatientLogMedicationConsumption_patientNotFoundError() throws Exception
  {
    // arrange
    // Scenario: patientId and medicationId are passed in POST object.
    // After confirming relevant Patient(.getMedications()) and Medication exist, save said Medication to MedicationLogRepository
    Long patientId = 1234L;
    Long medicationId = 4321L;
    when(patientRepo.findById(new Long(patientId))).thenReturn(Optional.ofNullable(oMockPatient));
    Collection<Medication> oMedicationCollection = new ArrayList();
    oMedicationCollection.add(oMockMedication);
    oMedicationCollection.add(oAnotherMockMedication);
    when(oMockPatient.getMedications()).thenReturn(oMedicationCollection);
    when(oMockMedication.getId()).thenReturn(new Long(medicationId));
    when(medicationRepo.findById(new Long(medicationId))).thenReturn(Optional.ofNullable(oMockMedication));
    // action
    // assert
    mvc.perform(
        post("/show-patients/0000/patient-medication-log-update").param("medicationId", "4321")
      ).andExpect(status().isNotFound()
          );
  }
  
  @Test(expected = NestedServletException.class)//MedicationNotFoundException.class
  public void assertPatientLogMedicationConsumption_medicationNotFoundError() throws Exception
  {
    // arrange
    // Scenario: patientId and medicationId are passed in POST object.
    // After confirming relevant Patient(.getMedications()) and Medication exist, save said Medication to MedicationLogRepository
    Long patientId = 1234L;
    Long medicationId = 4321L;
    Long lAnotherMedicationId = 5678L;
    when(patientRepo.findById(new Long(patientId))).thenReturn(Optional.ofNullable(oMockPatient));
    Collection<Medication> oMedicationCollection = new ArrayList();
    oMedicationCollection.add(oMockMedication);
    oMedicationCollection.add(oAnotherMockMedication);
    when(oMockPatient.getMedications()).thenReturn(oMedicationCollection);
    when(oMockMedication.getId()).thenReturn(new Long(medicationId));
    when(oAnotherMockMedication.getId()).thenReturn(new Long(lAnotherMedicationId));
    when(medicationRepo.findById(new Long(medicationId))).thenReturn(Optional.ofNullable(oMockMedication));
    // action
    // assert
    mvc.perform(
        post("/show-patients/1234/patient-medication-log-update").param("medicationId", "0000")
      ).andExpect(status().isNotFound()
          );
  }
}
