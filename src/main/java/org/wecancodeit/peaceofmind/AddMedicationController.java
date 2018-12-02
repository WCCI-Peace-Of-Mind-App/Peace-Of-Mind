package org.wecancodeit.peaceofmind;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping("/add/{id}/{genericName}/{dosage}/{administration}/{frequencyAmount}/{frequencyTime}/{picture}/{reason}")
	public String addMedicationToPatient(@PathVariable(value = "id") long id, @PathVariable(value = "genericName") String genericName, @PathVariable(value = "dosage") String dosage, 
			@PathVariable(value = "administration") String administration, @PathVariable(value= "frequencyAmount") int frequencyAmount,
			@PathVariable(value = "frequencyTime") String frequencyTime, @PathVariable(value = "picture") String picture, @PathVariable(value = "reason") String reason)
			throws PatientNotFoundException {
		Optional<Patient> patient = patientRepo.findById(id);

		if(patient.isPresent()) {
			doseFrequencyTimeEnum freqTime = doseFrequencyTimeEnum.fromString(frequencyTime);
			Medication newMed = new Medication(genericName, dosage, administration, frequencyAmount, freqTime, picture, reason);
			medicationRepo.save(newMed);
			patient.get().addMedication(newMed);
			return "partials/new-medication-added";
		}
		
		throw new PatientNotFoundException();
		
	}

}
