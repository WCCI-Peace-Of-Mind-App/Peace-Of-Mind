package org.wecancodeit.peaceofmind;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.util.NestedServletException;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
@RunWith(SpringRunner.class)
@WebMvcTest(PeaceOfMindController.class)
public class ViewTest {
  @Resource
  private MockMvc mvc;
  @MockBean
  private PatientRepository patientRepo;
  @MockBean
  private NonMedicalUserRepository nonMedUserRepo;
  @MockBean
  private MedicalUserRepository medUserRepo;
  @Mock
  private Patient oMockPatient;
  @Mock
  private Patient oAnotherMockPatient;
  @Mock
  private MedicalUser oMockMedUser;
  @Mock
  private MedicalUser oAnotherMockMedUser;
  @Mock
  private NonMedicalUser oMockNonMedUser;
  @Mock
  private NonMedicalUser oAnotherMockNonMedUser;
  
  @Test
  public void assertModelAllPatientsViewOk() throws Exception
  {
    Collection<Patient> oPatientsQueried = Arrays.asList(oMockPatient, oAnotherMockPatient); 
    when(patientRepo.findAll()).thenReturn(oPatientsQueried);
    mvc.perform(get("/all-patients")).andExpect(status().isOk());
    mvc.perform(get("/all-patients")).andExpect(view().name(is("patients")));
  }
  
  @Test(expected = NestedServletException.class) /* PatientNotFoundException */
  public void assertPatientsIsNotOkWithoutParameter() throws Exception
  {
    when(patientRepo.findById(new Long(1234L))).thenReturn(Optional.ofNullable(oMockPatient));
    mvc.perform(get("/patient?id=0")).andExpect(status().isNotFound());
  }
  
  @Test
  public void assertPatientIsOkViewIsPatient() throws Exception
  {
    when(patientRepo.findById(new Long(1234L))).thenReturn(Optional.ofNullable(oMockPatient));
    mvc.perform(get("/patient?id=1234")).andExpect(status().isOk());
    mvc.perform(get("/patient?id=1234")).andExpect(view().name(is("patient")));
  }
  
  
  @Test(expected = NestedServletException.class) /* MedicalUserNotFoundException */
  public void assertMedicalUserIsNotOkWithoutParameter() throws Exception
  {
    when(medUserRepo.findById(new Long(1234L))).thenReturn(Optional.ofNullable(oMockMedUser));
    mvc.perform(get("/medical-user?id=0")).andExpect(status().isNotFound());
  }

  @Test
  public void assertMedicalUserIsOkViewIsPatient() throws Exception
  {
    when(medUserRepo.findById(new Long(1234L))).thenReturn(Optional.ofNullable(oMockMedUser));
    mvc.perform(get("/medical-user?id=1234")).andExpect(status().isOk());
    mvc.perform(get("/medical-user?id=1234")).andExpect(view().name(is("medicalUser")));
  }

  @Test
  public void assertModelAllNonMedicalUserViewOk() throws Exception
  {
    Collection<NonMedicalUser> oNonMedUserQueried = Arrays.asList(oMockNonMedUser, oAnotherMockNonMedUser); 
    when(nonMedUserRepo.findAll()).thenReturn(oNonMedUserQueried);
    mvc.perform(get("/all-non-medical-users")).andExpect(status().isOk());
    mvc.perform(get("/all-non-medical-users")).andExpect(view().name(is("nonMedicalUsers")));
  }

  @Test(expected = NestedServletException.class) // NonMedicalUserNotFoundException
  public void assertNonMedicalUserIsNotOkWithoutParameter() throws Exception
  {
    when(nonMedUserRepo.findById(new Long(1234L))).thenReturn(Optional.ofNullable(oMockNonMedUser));
    mvc.perform(get("/non-medical-user?id=0")).andExpect(status().isNotFound());
  }

  @Test
  public void assertNonMedicalUserIsOkViewIsNonMedicalUser() throws Exception
  {
    when(nonMedUserRepo.findById(new Long(1234L))).thenReturn(Optional.ofNullable(oMockNonMedUser));
    mvc.perform(get("/non-medical-user?id=1234")).andExpect(status().isOk());
    mvc.perform(get("/non-medical-user?id=1234")).andExpect(view().name(is("nonMedicalUser")));
  }

}
