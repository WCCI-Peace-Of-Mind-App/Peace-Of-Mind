package org.wecancodeit.peaceofmind;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
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
		verify(model).addAttribute("patients", patientOne);
	}

	@Test
	public void shouldAddAllPatientsToModel() {
		Collection<Patient> allPatients = asList(patientOne, patientTwo);
		when(patientRepo.findAll()).thenReturn(allPatients);

		underTest.returnAllPatients(model);
		verify(model).addAttribute("patients", allPatients);
	}

	@Test
	public void shouldAddOneNonMedicalUserToModel() throws NonMedicalUserNotFoundException {
		when(nonMedUserRepo.findById(caregiverOneId)).thenReturn(Optional.of(caregiverOne));

		underTest.returnNonMedicalUser(caregiverOneId, model);
		verify(model).addAttribute("nonMedicalUsers", caregiverOne);
	}

	@Test
	public void shouldAddAllNonMedUsersToModel() {
		Collection<NonMedicalUser> allNonMedUsers = asList(caregiverOne, caregiverTwo);
		when(nonMedUserRepo.findAll()).thenReturn(allNonMedUsers);

		underTest.returnAllNonMedicalUsers(model);
		verify(model).addAttribute("nonMedicalUsers", allNonMedUsers);
	}

	@Test
	public void shouldAddOneMedicalUserToModel() throws MedicalUserNotFoundException {
		when(medUserRepo.findById(doctorId)).thenReturn(Optional.of(doctor));

		underTest.returnMedicalUser(doctorId, model);
		verify(model).addAttribute("medicalUsers", doctor);
	}

}
