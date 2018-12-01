package org.wecancodeit.peaceofmind;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PatientStatusController {
	
	@Resource
	private PatientStatusRepository patientStatusRepo;
	
	@Resource
	private PatientRepository patientRepo;

	@PostMapping("/add-status/{currentStatus}/{id}")
	public String addPatientStatus(@PathVariable(value = "currentStatus") String currentStatus, @PathVariable(value = "id") long patientId) throws PatientNotFoundException {
		String modCurrentStatus = currentStatus.toUpperCase();
		PatientStatusEnum statusEnum = PatientStatusEnum.valueOf(modCurrentStatus);
		Optional<Patient> patient = patientRepo.findById(patientId);
		
		if(patient.isPresent()) {
			PatientStatus newStatus = new PatientStatus(statusEnum, patient.get());
			patientStatusRepo.save(newStatus);
//			model.addAttribute("patient", patientRepo.findById(patientId));
			return "partials/patientStatus-update";			
		}
		
		throw new PatientNotFoundException();
		
	}

}
