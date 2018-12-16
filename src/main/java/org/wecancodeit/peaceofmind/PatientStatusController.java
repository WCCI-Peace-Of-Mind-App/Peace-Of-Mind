package org.wecancodeit.peaceofmind;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.wecancodeit.peaceofmind.users.Patient;
import org.wecancodeit.peaceofmind.users.PatientNotFoundException;
import org.wecancodeit.peaceofmind.users.PatientRepository;

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

	@GetMapping("/status-history-one/{id}")
	public String showSingleStatus(@PathVariable(value="id")long id, Model model) throws Exception {
		Optional<Patient> patient = patientRepo.findById(id);
		
		if(patient.isPresent()) {
			PatientStatus status = patientStatusRepo.findTop1ByPatientIdOrderByStatusDateTimeStampDesc(id);
			model.addAttribute("status", status);
			return "partials/statusChange-one";
		}
		throw new PatientNotFoundException();
	}

	@GetMapping("/status-history-three/{id}")
	public String findThreeRecentStatuses(@PathVariable(value = "id")long id, Model model) throws Exception {
		Optional<Patient> patient = patientRepo.findById(id);
		
		if(patient.isPresent()) {
			Collection<PatientStatus> patientStatuses = patientStatusRepo.findTop3ByPatientIdOrderByStatusDateTimeStampDesc(id);
			model.addAttribute("patientStatuses", patientStatuses);
			return "partials/statusChange-three";			
		}
		throw new PatientNotFoundException();
	}

	@GetMapping("/status-history-all/{id}")
	public String findAllStatuses(@PathVariable(value="id")long id, Model model) throws Exception{
		Optional<Patient> patient = patientRepo.findById(id);
		
		if(patient.isPresent()) {
			Collection<PatientStatus> allStatuses = patientStatusRepo.findByPatientIdOrderByStatusDateTimeStampDesc(id);
			model.addAttribute("patientStatuses", allStatuses);
			return "partials/statusChange-all";
		}
		throw new PatientNotFoundException();
	}
	
}
