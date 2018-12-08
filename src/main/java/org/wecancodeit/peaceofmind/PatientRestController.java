package org.wecancodeit.peaceofmind;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show-patients")
public class PatientRestController {
	
	@Resource
	private PatientRepository patientRepo;
	
	@RequestMapping("")
	public Iterable<Patient> findAllPatients() {
		return patientRepo.findAll();
	}
}
