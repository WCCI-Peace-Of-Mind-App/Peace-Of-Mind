package org.wecancodeit.peaceofmind.patientStatus;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.wecancodeit.peaceofmind.users.Patient;
import org.wecancodeit.peaceofmind.users.PatientRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(PatientStatusController.class)
public class PatientStatusControllerMockMvcTest {
	
	@Resource
	private MockMvc mvc;
	
	@MockBean
	private PatientStatusRepository patientStatusRepo;
	
	@MockBean
	private PatientRepository patientRepo;
	
	@Mock
	private PatientStatus status;
	
	@Mock
	private PatientStatus status2;
	
	@Mock
	private PatientStatus status3;
	
	@Mock
	private Patient patient;
	
	long arbitraryId = 1;
	
	@Test
	public void shouldBeOkToAddHappyToPatientStatus() throws Exception {
		when(patientRepo.findById(arbitraryId)).thenReturn(Optional.of(patient));
		
		mvc.perform(post("/add-status/happy/1"))
			.andExpect(status().isOk())
			.andExpect(view().name(is("partials/patientStatus-update")));
	}

	@Test
	public void shouldBeOkToAddSadToPatientStatus() throws Exception {
		when(patientRepo.findById(arbitraryId)).thenReturn(Optional.of(patient));
		
		mvc.perform(post("/add-status/sad/1"))
			.andExpect(status().isOk())
			.andExpect(view().name(is("partials/patientStatus-update")));
	}
	@Test
	public void shouldBeOkToAddAngryToPatientStatus() throws Exception {
		when(patientRepo.findById(arbitraryId)).thenReturn(Optional.of(patient));
		
		mvc.perform(post("/add-status/angry/1"))
			.andExpect(status().isOk())
			.andExpect(view().name(is("partials/patientStatus-update")));
	}

	@Test
	public void shouldBeOkToAddConfusedToPatientStatus() throws Exception {
		when(patientRepo.findById(arbitraryId)).thenReturn(Optional.of(patient));
		
		mvc.perform(post("/add-status/confused/1"))
			.andExpect(status().isOk())
			.andExpect(view().name(is("partials/patientStatus-update")));
	}
	
	@Test
	public void shouldBeOkToRouteTo3StatusView() throws Exception {
		when(patientRepo.findById(arbitraryId)).thenReturn(Optional.of(patient));
		Collection<PatientStatus> patientStatuses = Arrays.asList(status, status2, status3);
		when(patientStatusRepo.findTop3ByPatientIdOrderByStatusDateTimeStampDesc(arbitraryId)).thenReturn(patientStatuses);
		
		mvc.perform(get("/status-history-three/1"))
			.andExpect(status().isOk())
			.andExpect(view().name(is("partials/statusChange-three")))
			.andExpect(model().attribute("patientStatuses", patientStatuses));
	}
	
	@Test
	public void shouldBeOkToRouteToAllStatusHistoryView() throws Exception {
		when(patientRepo.findById(arbitraryId)).thenReturn(Optional.of(patient));
		Collection<PatientStatus> allStatuses = Arrays.asList(status, status2, status3);
		when(patientStatusRepo.findByPatientIdOrderByStatusDateTimeStampDesc(arbitraryId)).thenReturn(allStatuses);
		
		mvc.perform(get("/status-history-all/1"))
			.andExpect(status().isOk())
			.andExpect(view().name(is("partials/statusChange-all")))
			.andExpect(model().attribute("patientStatuses", allStatuses));
	}
	
	@Test
	public void shouldBeOkToReturnToOneView() throws Exception{
		when(patientRepo.findById(arbitraryId)).thenReturn(Optional.of(patient));
		when(patientStatusRepo.findTop1ByPatientIdOrderByStatusDateTimeStampDesc(arbitraryId)).thenReturn(status);

		mvc.perform(get("/status-history-one/1"))
			.andExpect(status().isOk())
			.andExpect(view().name(is("partials/statusChange-one")))
			.andExpect(model().attribute("status", status));
	}

}
