package org.wecancodeit.peaceofmind.medication;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.wecancodeit.peaceofmind.MedicalUser;
import org.wecancodeit.peaceofmind.MedicalUserRepository;
import org.wecancodeit.peaceofmind.Patient;
import org.wecancodeit.peaceofmind.PatientRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(AddMedicationController.class)
public class AddMedicationControllerMockMvcTest {

	@Resource
	private MockMvc mvc;
	
	@MockBean
	private PatientRepository patientRepo;
	
	@Mock
	private Patient patient;
	
	@MockBean
	private MedicationRepository medicationRepo;
	
	@Mock
	private Medication medication;
	
	@MockBean
	private MedicalUserRepository medUserRepo;
	
	@Mock
	private MedicalUser medUser;
	
	private long arbitraryId = 1;
	
	@Test
	public void shouldBeOkToGoToAddMedicationSite() throws Exception {
		when(medUserRepo.findById(arbitraryId)).thenReturn(Optional.of(medUser));
		when(patientRepo.findById(arbitraryId)).thenReturn(Optional.of(patient));
		
		mvc.perform(get("/add-medication/1/1"))
			.andExpect(status().isOk())
			.andExpect(view().name(is("add-medication")))
			.andExpect(model().attribute("medicalUser", medUser))
			.andExpect(model().attribute("patient", patient));
	}
	
	@Test
	public void shouldbeOkToAddMedicationToPatient() throws Exception {
		when(patientRepo.findById(arbitraryId)).thenReturn(Optional.of(patient));
		
		mvc.perform(post("/add-medication/add/1/generic/twice/oral/5/daily/o/placebo"))
			.andExpect(status().isOk())
			.andExpect(view().name(is("partials/new-medication-added")));
			
	}
	
	
}
