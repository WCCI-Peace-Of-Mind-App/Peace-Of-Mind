package org.wecancodeit.peaceofmind;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/add-medication")
public class AddMedicationController {
	
	@Resource
	private PatientRepository patientRepo;
	
	@Resource
	private MedicationRepository medicationRepo;

	@GetMapping("/{id}")
	public String returnPatientToAddMedication(@PathVariable(value="id")long id, Model model) throws PatientNotFoundException {
		Optional<Patient> patient = patientRepo.findById(id);
		
		if(patient.isPresent()) {
			model.addAttribute("patient", patient.get());
			return "add-medication";
		}
		
		throw new PatientNotFoundException();
	}

	public void addMedicationToPatient(String genericName, String dosage, String administration, int frequencyAmount,
			String frequencyTime, String picture, String reason, long id) {
		Optional<Patient> patient = patientRepo.findById(id);

		if(patient.isPresent()) {
			doseFrequencyTimeEnum freqTime = doseFrequencyTimeEnum.fromString(frequencyTime);
			Medication newMed = new Medication(genericName, dosage, administration, frequencyAmount, freqTime, picture, reason);
			medicationRepo.save(newMed);
			patient.get().addMedication(newMed);
		}
		
	}

}
