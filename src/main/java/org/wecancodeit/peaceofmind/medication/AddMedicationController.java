package org.wecancodeit.peaceofmind.medication;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wecancodeit.peaceofmind.users.*;

@Controller
@RequestMapping("/add-medication")
public class AddMedicationController {
	
	@Resource
	private PatientRepository patientRepo;
	
	@Resource
	private MedicationRepository medicationRepo;
	
	@Resource
	private MedicalUserRepository medUserRepo;

	@RequestMapping("/{id}/{medId}")
	public String returnPatientToAddMedication(@PathVariable(value="id")long patientId,@PathVariable(value = "medId") long medUserId, Model model) throws Exception {
		Optional<MedicalUser> medUser = medUserRepo.findById(medUserId);
		if(medUser.isPresent()) {
			Optional<Patient> patient = patientRepo.findById(patientId);
			
			if(patient.isPresent()) {
				model.addAttribute("medicalUser", medUser.get());
				model.addAttribute("patient", patient.get());
				return "add-medication";
			}
			
			throw new PatientNotFoundException();
		}
		throw new MedicalUserNotFoundException();
	}

	@PostMapping("/add/{id}/{genericName}/{dosage}/{administration}/{frequencyAmount}/{frequencyTime}/{picture}/{reason}")
	public String addMedicationToPatient(@PathVariable(value = "id") long id, @PathVariable(value = "genericName") String genericName, @PathVariable(value = "dosage") String dosage, 
			@PathVariable(value = "administration") String administration, @PathVariable(value= "frequencyAmount") int frequencyAmount,
			@PathVariable(value = "frequencyTime") String frequencyTime, @PathVariable(value = "picture") String picture, @PathVariable(value = "reason") String reason)
			throws PatientNotFoundException {
		Optional<Patient> patient = patientRepo.findById(id);

		if(patient.isPresent()) {
			doseFrequencyTimeEnum freqTime = doseFrequencyTimeEnum.fromString(frequencyTime);
			AdministrationEnum adminValue = AdministrationEnum.valueOf(administration.toUpperCase());
			Medication newMed = new Medication(genericName, dosage, adminValue, frequencyAmount, freqTime, picture, reason);
			patient.get().addMedication(newMed);
			medicationRepo.save(newMed);
			return "partials/new-medication-added";
		}
		
		throw new PatientNotFoundException();
		
	}

}
