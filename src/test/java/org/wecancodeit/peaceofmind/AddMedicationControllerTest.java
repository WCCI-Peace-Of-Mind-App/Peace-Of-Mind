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
import org.springframework.ui.Model;

public class AddMedicationControllerTest {

	@InjectMocks
	private AddMedicationController underTest;
	
	@Mock
	private PatientRepository patientRepo;
	
	@Mock
	private MedicationRepository medicationRepo;
	
	@Mock
	private MedicalUserRepository medUserRepo;
	
	@Mock
	private Patient testPatient;
	
	@Mock
	private Model model;
	
	private long arbitraryId;
	
	String genericName;
	String dosage;
	String administration;
	int frequencyAmount;
	String frequencyTime;
	//enum Daily, Weekly, Monthly, As_Needed
	String picture;
	String reason;

	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		genericName = "drugs";
		dosage = "twice";
		administration = "oral";
		frequencyAmount = 5;
		frequencyTime = "daily";
		picture = null;
		reason = "placebo";
		
	}
	
	@Test
	public void shouldAddPatientToModel() throws Exception {
		when(patientRepo.findById(arbitraryId)).thenReturn(Optional.of(testPatient));
		
		underTest.returnPatientToAddMedication(arbitraryId, model);
		verify(model).addAttribute("patient", testPatient);
	}
	
	@Test
	public void shouldAddNewMedicationToPatient() {
		when(patientRepo.findById(arbitraryId)).thenReturn(Optional.of(testPatient));
		
		underTest.addMedicationToPatient(genericName, dosage, administration, frequencyAmount, frequencyTime, picture, reason, arbitraryId);
		
		ArgumentCaptor<Medication> medicationArgument = ArgumentCaptor.forClass(Medication.class);
		verify(medicationRepo).save(medicationArgument.capture());
		assertEquals(genericName, medicationArgument.getValue().getGenericName());
	}
	
}
