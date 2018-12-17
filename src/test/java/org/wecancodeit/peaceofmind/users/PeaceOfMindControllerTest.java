package org.wecancodeit.peaceofmind.users;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class PeaceOfMindControllerTest {

	@InjectMocks
	private PeaceOfMindController underTest;

	@Mock
	private Patient patientOne;
	long patientOneId;

	@Mock
	private Patient patientTwo;

	@Mock
	private NonMedicalUser caregiverOne;
	long caregiverOneId;

	@Mock
	private NonMedicalUser caregiverTwo;

	@Mock
	private MedicalUser doctor;
	long doctorId;

	@Mock
	private MedicalUser nurse;


	@Mock
	private PatientRepository patientRepo;

	@Mock
	private NonMedicalUserRepository nonMedUserRepo;

	@Mock
	private MedicalUserRepository medUserRepo;

	@Mock
	private Model model;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldAddOnePatientToModel() throws PatientNotFoundException {
		when(patientRepo.findById(patientOneId)).thenReturn(Optional.of(patientOne));

		underTest.returnPatient(patientOneId, model);
		verify(model).addAttribute("patient", patientOne);
	}


	@Test
	public void shouldAddOneNonMedicalUserToModel() throws NonMedicalUserNotFoundException {
		when(nonMedUserRepo.findById(caregiverOneId)).thenReturn(Optional.of(caregiverOne));

		underTest.returnNonMedicalUser(caregiverOneId, model);
		verify(model).addAttribute("nonMedicalUser", caregiverOne);
	}

	@Test
	public void shouldAddOneMedicalUserToModel() throws MedicalUserNotFoundException {
		when(medUserRepo.findById(doctorId)).thenReturn(Optional.of(doctor));

		underTest.returnMedicalUser(doctorId, model);
		verify(model).addAttribute("medicalUser", doctor);
	}

}
