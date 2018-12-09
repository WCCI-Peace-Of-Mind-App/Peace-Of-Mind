package org.wecancodeit.peaceofmind;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
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

	@Resource
	MedicationLogRepository medLogRepo;

	@Resource
	PatientStatusRepository patientStatusRepo;

	@Resource
	MedicationTrackerRepository medTrackerRepo;
	
	@Resource
	DiaryRepository diaryRepo;

	@RequestMapping("/patient")
	public String returnPatient(@RequestParam(value = "id") long id, Model model) throws PatientNotFoundException {
		Optional<Patient> patient = patientRepo.findById(id);

		if (patient.isPresent()) {
			model.addAttribute("patient", patient.get());
			return "patient";
		}

		throw new PatientNotFoundException();

	}

	@RequestMapping("/patient-home")
	public String returnPatientHomePage(@RequestParam(value = "id") long id, Model model)
			throws PatientNotFoundException {
		Optional<Patient> patient = patientRepo.findById(id);
		MedicalUser medUser = patient.get().getMedicalUser();
		NonMedicalUser nonMedUser = patient.get().getNonMedicalUser();

		if (patient.isPresent()) {
			model.addAttribute("patient", patient.get());
			model.addAttribute("medicalUser", medUser);
			model.addAttribute("nonMedicalUser", nonMedUser);
			return "patient-Home";
		}

		throw new PatientNotFoundException();

	}
	
	@RequestMapping("/patient-diary")
	public String returnPatientDiaryPage(@RequestParam(value = "id") long id, Model model) 	throws PatientNotFoundException {
		Optional<Patient> patient = patientRepo.findById(id);
		Collection<Diary> diaryEntries = diaryRepo.findAllByPatient(patient.get());

		if (patient.isPresent()) {
			model.addAttribute("patient", patient.get());
			if (diaryEntries.size() > 0) {
			model.addAttribute("diarys", diaryEntries);
			}

			return "patient-Diary";
		}

		throw new PatientNotFoundException();
	}
	
	@RequestMapping("/add-diaryEntry")
	public String addDiaryEntry(String entryText, long patientId) {
		Patient author = patientRepo.findById(patientId).get();

			Diary newEntry = new Diary(entryText, author);
			diaryRepo.save(newEntry);
			
			return "redirect:/patient-diary?id=" + patientId;
		}

	@RequestMapping("/non-medical-user")
	public String returnNonMedicalUser(@RequestParam(value = "id") long id, Model model)
			throws NonMedicalUserNotFoundException {
		Optional<NonMedicalUser> nonMedUser = nonMedUserRepo.findById(id);

		if (nonMedUser.isPresent()) {
			model.addAttribute("nonMedicalUser", nonMedUser.get());

			return "nonMedicalUser";
		}

		throw new NonMedicalUserNotFoundException();

	}

	@RequestMapping("/non-medical-user-home")
	public String returnNonMedicalUserHomePage(@RequestParam(value = "id") long id, Model model)
			throws NonMedicalUserNotFoundException {

		DateTimeFormatter yyyymm = DateTimeFormatter.ofPattern("yyyy/MM");

		String thisMonth = LocalDateTime.now().format(yyyymm);

		Optional<NonMedicalUser> nonMedUser = nonMedUserRepo.findById(id);
		Patient patient = nonMedUser.get().getPatient();
		Long patientId = patient.getId();
		PatientStatus currentStatus = patientStatusRepo.findTop1ByParentIdOrderByStatusDateTimeStampDesc(patientId);
		Collection<Medication> medications = patient.getMedications();

		String currentMonthStart = thisMonth + "/01";

		if (medications != null) {

			Collection<MedicationTracker> medicationTrackers = new HashSet<>();

			for (Medication medication : medications) {
				
				int missedDose = 0;
				
				medicationTrackers = medTrackerRepo.findAllByMedicationAndDateGreaterThanEqual(medication, currentMonthStart);
				
				for (MedicationTracker medTracker : medicationTrackers) {
					if (medTracker.getDosesMissed() > 0
							&& medication.getFrequencyTime() != doseFrequencyTimeEnum.As_Needed) {
						missedDose++; 
					}
				} if (missedDose > 0) {
						medication.setAdherentThisMonth("/images/false.png");
					} else {
						medication.setAdherentThisMonth("/images/true.png");
					}
				medRepo.save(medication);
				}
			}

			if (nonMedUser.isPresent()) {
				model.addAttribute("nonMedicalUser", nonMedUser.get());
				model.addAttribute("status", currentStatus);
				
				if (medications != null) {
					model.addAttribute("medications", medications);
				}

			return "nonMedicalUser-Home";
		}

		throw new NonMedicalUserNotFoundException();

	}

	@RequestMapping("/medical-user")
	public String returnMedicalUser(@RequestParam(value = "id") long id, Model model)
			throws MedicalUserNotFoundException {
		Optional<MedicalUser> medUser = medUserRepo.findById(id);

		if (medUser.isPresent()) {
			model.addAttribute("medicalUser", medUser.get());
			return "medicalUser";
		}

		throw new MedicalUserNotFoundException();

	}

	@RequestMapping("/medical-user-home")
	public String returnMedicalUserHomePage(@RequestParam(value = "id") long id, Model model)
			throws MedicalUserNotFoundException {
		DateTimeFormatter yyyymm = DateTimeFormatter.ofPattern("yyyy/MM");

		String thisMonth = LocalDateTime.now().format(yyyymm);

		Optional<MedicalUser> medUser = medUserRepo.findById(id);
		NonMedicalUser nonMed = medUser.get().getPatient().getNonMedicalUser();
		Patient patient = medUser.get().getPatient();
		Long patientId = patient.getId();
		PatientStatus currentStatus = patientStatusRepo.findTop1ByParentIdOrderByStatusDateTimeStampDesc(patientId);
		Collection<Medication> medications = patient.getMedications();

		String currentMonthStart = thisMonth + "/01";

		if (medications != null) {

			Collection<MedicationTracker> medicationTrackers = new HashSet<>();

			for (Medication medication : medications) {
				
				int missedDose = 0;
				
				medicationTrackers = medTrackerRepo.findAllByMedicationAndDateGreaterThanEqual(medication, currentMonthStart);
				
				for (MedicationTracker medTracker : medicationTrackers) {
					if (medTracker.getDosesMissed() > 0
							&& medication.getFrequencyTime() != doseFrequencyTimeEnum.As_Needed) {
						missedDose++; 
					}
				} if (missedDose > 0) {
						medication.setAdherentThisMonth("/images/false.png");
					} else {
						medication.setAdherentThisMonth("/images/true.png");
					}
				medRepo.save(medication);
				}
			}

			if (medUser.isPresent()) {
				model.addAttribute("medicalUser", medUser.get());
				model.addAttribute("nonMedicalUser", nonMed);
				model.addAttribute("patient", patient);
				model.addAttribute("status", currentStatus);
				
				if (medications != null) {
					model.addAttribute("medications", medications);
				}
			return "medicalUser-Home";
		}

		throw new MedicalUserNotFoundException();

	}


	@RequestMapping("/my-medications")
	public String returnPatientMedications(@RequestParam(value = "id") long id, Model model)
			throws PatientNotFoundException {
		DateTimeFormatter yyyymmdd = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String today = LocalDateTime.now().format(yyyymmdd);
		
		Optional<Patient> patient = patientRepo.findById(id);
		Collection<Medication> meds = patient.get().getMedications();
		Collection<MedicationTracker> medTrackers = new HashSet<>();
		
		for(Medication med : meds) {
			medTrackers.add(medTrackerRepo.findByMedicationAndDate(med, today));
		}

		if (patient.isPresent()) {
			model.addAttribute("patient", patient.get());
			model.addAttribute("medications", meds);
			model.addAttribute("medTrackers", medTrackers);
			return "my-Medications";
		}

		throw new PatientNotFoundException();
	}

	@RequestMapping("/med-tracker-history-non-med")
	public String returnMedTrackerHistory(@RequestParam(value = "id") long id, Model model) {

		DateTimeFormatter yyyy = DateTimeFormatter.ofPattern("yyyy");
		String currentYear = LocalDateTime.now().format(yyyy);
		String startDate = currentYear + "/01/01";

		Optional<Patient> patient = patientRepo.findById(id);
		NonMedicalUser nonMedUser = patient.get().getNonMedicalUser();

		Collection<Medication> medications = patient.get().getMedications();

		model.addAttribute("nonMedicalUser", nonMedUser);

		if (patient.isPresent()) {
			model.addAttribute("patient", patient.get());
		}

		if (medications != null) {

			Collection<MedicationTracker> medicationTrackers = new HashSet<>();

			for (Medication medication : medications) {

				medicationTrackers
						.addAll(medTrackerRepo.findAllByMedicationAndDateGreaterThanEqual(medication, startDate));
			}

			if (medicationTrackers.size() > 0) {
				model.addAttribute("medicationTrackers", medicationTrackers);
			}
		}

		return "med-Tracker-History-Non-Med";

	}
	
	@RequestMapping("/med-tracker-history-med")
	public String returnMedTrackerHistoryMedUser(@RequestParam(value = "id") long id, Model model) {

		DateTimeFormatter yyyy = DateTimeFormatter.ofPattern("yyyy");
		String currentYear = LocalDateTime.now().format(yyyy);
		String startDate = currentYear + "/01/01";

		Optional<Patient> patient = patientRepo.findById(id);
		MedicalUser medUser = patient.get().getMedicalUser();

		Collection<Medication> medications = patient.get().getMedications();

		model.addAttribute("medicalUser", medUser);

		if (patient.isPresent()) {
			model.addAttribute("patient", patient.get());
		}

		if (medications != null) {

			Collection<MedicationTracker> medicationTrackers = new HashSet<>();

			for (Medication medication : medications) {

				medicationTrackers
						.addAll(medTrackerRepo.findAllByMedicationAndDateGreaterThanEqual(medication, startDate));
			}

			if (medicationTrackers.size() > 0) {
				model.addAttribute("medicationTrackers", medicationTrackers);
			}
		}

		return "med-Tracker-History-Med";

	}

}
