package org.wecancodeit.peaceofmind;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
			return "partials/patientStatus-update";			
		}
		
		throw new PatientNotFoundException();
		
	}

	@GetMapping("/status-history")
	public String findThreeRecentStatuses(@RequestParam(value = "id")long id, Model model) throws Exception {
		Optional<Patient> patient = patientRepo.findById(id);
		
		if(patient.isPresent()) {
			Collection<PatientStatus> patientStatuses = patientStatusRepo.findTop3ByParentIdOrderByStatusDateTimeStampDesc(id);
			model.addAttribute("patientStatuses", patientStatuses);
			model.addAttribute("patient", patient.get());
			return "status-history";			
		}
		throw new PatientNotFoundException();
	}

}
