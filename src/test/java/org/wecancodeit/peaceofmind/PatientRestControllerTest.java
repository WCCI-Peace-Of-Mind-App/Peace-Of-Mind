package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
@RunWith(SpringRunner.class)
@WebMvcTest(PeaceOfMindController.class)
public class PatientRestControllerTest {
  
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
  @Autowired
  private ObjectMapper jsonMapper;
  
  @Test
  public void assertPatientLogMedicationConsumption_returnJson() throws Exception
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
    mvc.perform(
        post("/patient-medication-log-update").param("patientId", "1234").param("medicationId", "4321")
      ).andExpect(status().isOk()).andExpect(view().name(is("partials/med-log-update-confirmation.html")))
      .andExpect(content().contentType("text/html;charset=UTF-8"));
//      .andExpect(model().attributeExists("patient"))
//      .andExpect(model().attribute("patient", is(oMockPatient)));
  }
}
