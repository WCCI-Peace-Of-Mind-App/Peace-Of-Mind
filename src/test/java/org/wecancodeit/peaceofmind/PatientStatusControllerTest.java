package org.wecancodeit.peaceofmind;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PatientStatusControllerTest {
	
	@InjectMocks
	private PatientStatusController underTest;
	
	@Mock
	private PatientStatusRepository patientStatusRepo;
	
	@Mock
	private PatientRepository patientRepo;
	
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
	

}
