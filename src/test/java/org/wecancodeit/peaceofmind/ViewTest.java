package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

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
	@MockBean
	private MedicationRepository medicationRepo;
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
@WithMockUser("xxGAMERxx")
	@Test
	public void assertModelAllPatientsViewOk() throws Exception {
		// arrange
		Collection<Patient> oPatientsQueried = Arrays.asList(oMockPatient, oAnotherMockPatient);
		when(patientRepo.findAll()).thenReturn(oPatientsQueried);
		// action
		// assert
		mvc.perform(get("/all-patients")).andExpect(status().isOk()).andExpect(view().name(is("patients")))
				.andExpect(model().attributeExists("patients"))
				.andExpect(content().contentType("text/html;charset=UTF-8"))
				.andExpect(model().attribute("patients", is(oPatientsQueried)));

	}
@WithMockUser("xxGAMERxx")
	@Test(expected = NestedServletException.class) /* PatientNotFoundException */
	public void assertPatientsIsNotOkWithoutParameter() throws Exception {
		// arrange
		when(patientRepo.findById(new Long(1234L))).thenReturn(Optional.ofNullable(oMockPatient));
		// action
		// assert
		mvc.perform(get("/patient?id=0")).andExpect(status().isNotFound());
	}
@WithMockUser("docOc")
	@Test
	public void assertPatientIsOkViewIsPatient() throws Exception {
		// arrange
		when(patientRepo.findById(new Long(1234L))).thenReturn(Optional.ofNullable(oMockPatient));
		// action
		// assert
		mvc.perform(get("/patient?id=1234")).andExpect(status().isOk()).andExpect(view().name(is("patient")))
				.andExpect(content().contentType("text/html;charset=UTF-8"))
				.andExpect(model().attributeExists("patients"))
				.andExpect(model().attribute("patients", is(oMockPatient)));
	}

@WithMockUser(value = "docOc")
	@Test(expected = NestedServletException.class) /* MedicalUserNotFoundException */
	public void assertMedicalUserIsNotOkWithoutParameter() throws Exception {
		// arrange
		when(medUserRepo.findById(new Long(1234L))).thenReturn(Optional.ofNullable(oMockMedUser));
		// action
		// assert
		mvc.perform(get("/medical-user?id=0")).andExpect(status().isNotFound())
				.andExpect(model().attributeHasErrors("medicalUsers"));
	}

	@WithMockUser(value = "docOc")
@Test
	public void assertMedicalUserIsOkViewIsMedicalUser() throws Exception {
		// arrange
		when(medUserRepo.findById(new Long(1234L))).thenReturn(Optional.ofNullable(oMockMedUser));
		// action
		// assert
		mvc.perform(get("/medical-user?id=1234")).andExpect(status().isOk()).andExpect(view().name(is("medicalUser")))
				.andExpect(content().contentType("text/html;charset=UTF-8"))
				.andExpect(model().attributeExists("medicalUsers"))
				.andExpect(model().attribute("medicalUsers", is(oMockMedUser)));
	}

	@WithMockUser(value = "xxGAMERxx")
@Test
	public void assertModelAllNonMedicalUserViewOk() throws Exception {
		// arrange
		Collection<NonMedicalUser> oNonMedUserQueried = Arrays.asList(oMockNonMedUser, oAnotherMockNonMedUser);
		when(nonMedUserRepo.findAll()).thenReturn(oNonMedUserQueried);
		// action
		// assert
		mvc.perform(get("/all-non-medical-users")).andExpect(status().isOk())
				.andExpect(view().name(is("nonMedicalUsers")))
				.andExpect(content().contentType("text/html;charset=UTF-8"))
				.andExpect(model().attributeExists("nonMedicalUsers"))
				.andExpect(model().attribute("nonMedicalUsers", oNonMedUserQueried));
	}

	@WithMockUser(value = "docOc")
@Test(expected = NestedServletException.class) // NonMedicalUserNotFoundException
	public void assertNonMedicalUserIsNotOkWithoutParameter() throws Exception {
		// arrange
		when(nonMedUserRepo.findById(new Long(1234L))).thenReturn(Optional.ofNullable(oMockNonMedUser));
		// action
		// assert
		mvc.perform(get("/non-medical-user?id=0")).andExpect(status().isNotFound())
				.andExpect(model().attributeHasErrors("nonMedicalUsers"));
	}

	@WithMockUser(value = "xxGAMERxx")
@Test
	public void assertNonMedicalUserIsOkViewIsNonMedicalUser() throws Exception {
		when(nonMedUserRepo.findById(new Long(1234L))).thenReturn(Optional.ofNullable(oMockNonMedUser));
		mvc.perform(get("/non-medical-user?id=1234")).andExpect(status().isOk())
				.andExpect(view().name(is("nonMedicalUser")))
				.andExpect(content().contentType("text/html;charset=UTF-8"))
				.andExpect(model().attributeExists("nonMedicalUsers"))
				.andExpect(model().attribute("nonMedicalUsers", oMockNonMedUser));
	}

	@WithMockUser(value = "xxGAMERxx")
@Test
	public void assertNonMedicalUserHomeIsOKViewIsNonMedicalUserHome() throws Exception {
		when(nonMedUserRepo.findById(new Long(1234L))).thenReturn(Optional.ofNullable(oMockNonMedUser));
		mvc.perform(get("/non-medical-user-home?id=1234")).andExpect(status().isOk())
				.andExpect(view().name(is("nonMedicalUser-Home")))
				.andExpect(content().contentType("text/html;charset=UTF-8"))
				.andExpect(model().attributeExists("nonMedicalUsers"))
				.andExpect(model().attribute("nonMedicalUsers", oMockNonMedUser));
	}

}
