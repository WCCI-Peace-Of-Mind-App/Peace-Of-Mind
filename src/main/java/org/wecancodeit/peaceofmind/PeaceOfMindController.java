package org.wecancodeit.peaceofmind;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PeaceOfMindController {

	@Resource
	PatientRepository patientRepo;

	@Resource
	NonMedicalUserRepository nonMedUserRepo;

	@Resource
	MedicalUserRepository medUserRepo;

	
	@Resource
	MedicationRepository medRepo;
	
	@RequestMapping("/patient")
	public String returnPatient(@RequestParam(value = "id") long id, Model model) throws PatientNotFoundException {
		Optional<Patient> patient = patientRepo.findById(id);

		if (patient.isPresent()) {
			model.addAttribute("patients", patient.get());
			return "patient";
		}

		throw new PatientNotFoundException();

	}

	@RequestMapping("/all-patients")
	public String returnAllPatients(Model model) {
		model.addAttribute("patients", patientRepo.findAll());
		return "patients";

	}

	@RequestMapping("/non-medical-user")
	public String returnNonMedicalUser(@RequestParam(value = "id") long id, Model model)
			throws NonMedicalUserNotFoundException {
		Optional<NonMedicalUser> nonMedUser = nonMedUserRepo.findById(id);

		if (nonMedUser.isPresent()) {
			model.addAttribute("nonMedicalUsers", nonMedUser.get());
			return "nonMedicalUser";
		}

		throw new NonMedicalUserNotFoundException();

	}
	
	@RequestMapping("/non-medical-user-home")
	public String returnNonMedicalUserHomePage(@RequestParam(value = "id") long id, Model model)
			throws NonMedicalUserNotFoundException {
		Optional<NonMedicalUser> nonMedUser = nonMedUserRepo.findById(id);

		if (nonMedUser.isPresent()) {
			model.addAttribute("nonMedicalUsers", nonMedUser.get());
			return "nonMedicalUser-Home";
		}

		throw new NonMedicalUserNotFoundException();

	}

	@RequestMapping("/all-non-medical-users")
	public String returnAllNonMedicalUsers(Model model) {
		model.addAttribute("nonMedicalUsers", nonMedUserRepo.findAll());
		return "nonMedicalUsers";

	}

	@RequestMapping("/medical-user")
	public String returnMedicalUser(@RequestParam(value = "id") long id, Model model)
			throws MedicalUserNotFoundException {
		Optional<MedicalUser> medUser = medUserRepo.findById(id);

		if (medUser.isPresent()) {
			model.addAttribute("medicalUsers", medUser.get());
			return "medicalUser";
		}

		throw new MedicalUserNotFoundException();

	}

	@RequestMapping("/medication")
	public String returnMedications(@RequestParam(value = "id") long id, Model model)
			throws MedicationNotFoundException {
		Optional<Medication> med = medRepo.findById(id);

		if (med.isPresent()) {
			model.addAttribute("medications", med.get());
			return "medication";
		}

		throw new MedicationNotFoundException();

	}
	
	@RequestMapping("/all-medications")
	public String returnAllMedications(Model model) {
		model.addAttribute("medications", medRepo.findAll());
		return "medications";
	}
  @RequestMapping("/login")
  public String login() {
    return "auth/login";
  }
}
