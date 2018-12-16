package org.wecancodeit.peaceofmind.users;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;
import org.wecancodeit.peaceofmind.diary.DiaryRepository;
import org.wecancodeit.peaceofmind.medication.*;
import org.wecancodeit.peaceofmind.*;

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
	@MockBean
	private MedicationLogRepository medLogRepo;
	@MockBean
	private PatientStatusRepository patientStatusRepo;
	@MockBean
	private MedicationTrackerRepository medTrackerRepo;
	@MockBean
	private DiaryRepository diaryRepo;
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
	@Mock
	private PatientStatus patientStatus;
	
	long arbitraryId = 1;


	@Test(expected = NestedServletException.class) /* PatientNotFoundException */
	public void assertPatientsIsNotOkWithoutParameter() throws Exception {
		// arrange
		when(patientRepo.findById(new Long(1234L))).thenReturn(Optional.ofNullable(oMockPatient));
		// action
		// assert
		mvc.perform(get("/patient?id=0")).andExpect(status().isNotFound());
	}

	@Test
	public void assertPatientIsOkViewIsPatient() throws Exception {
		// arrange
		when(patientRepo.findById(new Long(1234L))).thenReturn(Optional.ofNullable(oMockPatient));
		// action
		// assert
		mvc.perform(get("/patient?id=1234")).andExpect(status().isOk()).andExpect(view().name(is("patient")))
				.andExpect(content().contentType("text/html;charset=UTF-8"))
				.andExpect(model().attributeExists("patient"))
				.andExpect(model().attribute("patient", is(oMockPatient)));
	}

	@Test(expected = NestedServletException.class) /* MedicalUserNotFoundException */
	public void assertMedicalUserIsNotOkWithoutParameter() throws Exception {
		// arrange
		when(medUserRepo.findById(new Long(1234L))).thenReturn(Optional.ofNullable(oMockMedUser));
		// action
		// assert
		mvc.perform(get("/medical-user?id=0")).andExpect(status().isNotFound())
				.andExpect(model().attributeHasErrors("medicalUsers"));
	}
	@Test(expected = NestedServletException.class) // html template using syntax that doesn't require adding all idv items to model
	public void assertMedicalUserIsOkViewIsPatient() throws Exception {
		// arrange
		when(medUserRepo.findById(new Long(1234L))).thenReturn(Optional.ofNullable(oMockMedUser));
		// action
		// assert
		mvc.perform(get("/medical-user?id=1234")).andExpect(status().isOk()).andExpect(view().name(is("medicalUser")))
				.andExpect(content().contentType("text/html;charset=UTF-8"))
				.andExpect(model().attributeExists("medicalUser"))
				.andExpect(model().attribute("medicalUser", is(oMockMedUser)));
	}


	@Test(expected = NestedServletException.class) // NonMedicalUserNotFoundException
	public void assertNonMedicalUserIsNotOkWithoutParameter() throws Exception {
		// arrange
		when(nonMedUserRepo.findById(new Long(1234L))).thenReturn(Optional.ofNullable(oMockNonMedUser));
		// action
		// assert
		mvc.perform(get("/non-medical-user?id=0")).andExpect(status().isNotFound())
				.andExpect(model().attributeHasErrors("nonMedicalUsers"));
	}

	@Test(expected = NestedServletException.class)
	public void assertNonMedicalUserIsOkViewIsNonMedicalUser() throws Exception {
		when(nonMedUserRepo.findById(new Long(1234L))).thenReturn(Optional.ofNullable(oMockNonMedUser));
		mvc.perform(get("/non-medical-user?id=1234")).andExpect(status().isOk())
				.andExpect(view().name(is("nonMedicalUser")))
				.andExpect(content().contentType("text/html;charset=UTF-8"))
				.andExpect(model().attributeExists("nonMedicalUser"))
				.andExpect(model().attribute("nonMedicalUser", oMockNonMedUser));
	}

	@Test
	public void assertNonMedicalUserHomeIsOKViewIsNonMedicalUserHome() throws Exception {
		when(nonMedUserRepo.findById(new Long(1234L))).thenReturn(Optional.ofNullable(oMockNonMedUser));
		when(oMockNonMedUser.getPatient()).thenReturn(oMockPatient);
		when(oMockPatient.getId()).thenReturn(arbitraryId);
		when(patientStatusRepo.findTop1ByPatientIdOrderByStatusDateTimeStampDesc(arbitraryId)).thenReturn(patientStatus);
		mvc.perform(get("/non-medical-user-home?id=1234")).andExpect(status().isOk())
				.andExpect(view().name(is("nonMedicalUser-Home")))
				.andExpect(content().contentType("text/html;charset=UTF-8"))
				.andExpect(model().attributeExists("nonMedicalUser"))
				.andExpect(model().attribute("nonMedicalUser", oMockNonMedUser));
	}

}
