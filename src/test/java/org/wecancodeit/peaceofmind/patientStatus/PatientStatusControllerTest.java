package org.wecancodeit.peaceofmind.patientStatus;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.wecancodeit.peaceofmind.users.Patient;
import org.wecancodeit.peaceofmind.users.PatientRepository;

public class PatientStatusControllerTest {
	
	@InjectMocks
	private PatientStatusController underTest;
	
	@Mock
	private Model model;
	
	@Mock
	private PatientStatusRepository patientStatusRepo;
	
	@Mock
	private PatientRepository patientRepo;
	
	@Mock
	private PatientStatus ps1;
	
	@Mock
	private PatientStatus ps2;
	
	@Mock
	private PatientStatus ps3;
	
	@Mock
	private PatientStatus ps4;
	
	@Mock
	private Patient patient;
	private PatientStatusEnum testStatus;
	private PatientStatusEnum testStatus2;
	
	long arbitraryId = 1L;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		testStatus = PatientStatusEnum.HAPPY;
		testStatus2 = PatientStatusEnum.ANGRY;
	}
	
	@Test
	public void shouldAddPatientStatus() throws Exception {
		when(patientRepo.findById(arbitraryId)).thenReturn(Optional.of(patient));
		
		underTest.addPatientStatus("happy", arbitraryId);
		
		ArgumentCaptor<PatientStatus> patientStatusArgument = ArgumentCaptor.forClass(PatientStatus.class);
		verify(patientStatusRepo).save(patientStatusArgument.capture());
		assertEquals(testStatus, patientStatusArgument.getValue().getStatus());
	}
	
	@Test
	public void shouldAddNotWellPatientStatus() throws Exception {
		when(patientRepo.findById(arbitraryId)).thenReturn(Optional.of(patient));
		
		underTest.addPatientStatus("angry", arbitraryId);
		
		ArgumentCaptor<PatientStatus> patientStatusArgument = ArgumentCaptor.forClass(PatientStatus.class);
		verify(patientStatusRepo).save(patientStatusArgument.capture());
		assertEquals(testStatus2, patientStatusArgument.getValue().getStatus());
	}
	
	@Test
	public void shouldFindTop3ResultsForPatientStatus() throws Exception {
		Collection<PatientStatus> patientStatuses = Arrays.asList(ps1, ps2, ps3);
		
		when(patientStatusRepo.findTop3ByPatientIdOrderByStatusDateTimeStampDesc(arbitraryId)).thenReturn(patientStatuses);
		when(patientRepo.findById(arbitraryId)).thenReturn(Optional.of(patient));
		
		underTest.findThreeRecentStatuses(arbitraryId, model);
		verify(model).addAttribute("patientStatuses", patientStatuses);
	}
	
	@Test
	public void shouldFindAllResultsForPatientStatus() throws Exception {
		Collection<PatientStatus> allStatuses = Arrays.asList(ps1, ps2, ps3, ps4);
		
		when(patientStatusRepo.findByPatientIdOrderByStatusDateTimeStampDesc(arbitraryId)).thenReturn(allStatuses);
		when(patientRepo.findById(arbitraryId)).thenReturn(Optional.of(patient));
		
		underTest.findAllStatuses(arbitraryId, model);
		verify(model).addAttribute("patientStatuses", allStatuses);	
	}
	
}
