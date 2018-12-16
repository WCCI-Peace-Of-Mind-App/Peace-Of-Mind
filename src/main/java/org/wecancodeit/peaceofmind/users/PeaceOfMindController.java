package org.wecancodeit.peaceofmind.users;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.wecancodeit.peaceofmind.diary.*;
import org.wecancodeit.peaceofmind.medication.*;
import org.wecancodeit.peaceofmind.*;

@Controller
public class PeaceOfMindController {

	@Resource
	private PatientRepository patientRepo;

	@Resource
	private NonMedicalUserRepository nonMedUserRepo;

	@Resource
	private MedicalUserRepository medUserRepo;

	@Resource
	private MedicationRepository medRepo;

	@Resource
	private MedicationLogRepository medLogRepo;

	@Resource
	private PatientStatusRepository patientStatusRepo;

	@Resource
	private MedicationTrackerRepository medTrackerRepo;
	
	@Resource
	private DiaryRepository diaryRepo;

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

		if (patient.isPresent()) {
			model.addAttribute("patient", patient.get());

			MedicalUser medUser = patient.get().getMedicalUser();
			model.addAttribute("medicalUser", medUser);

			NonMedicalUser nonMedUser = patient.get().getNonMedicalUser();
			model.addAttribute("nonMedicalUser", nonMedUser);

			return "patient-Home";
		}
		throw new PatientNotFoundException();

	}
	
	@RequestMapping("/patient-diary")
	public String returnPatientDiaryPage(@RequestParam(value = "id") long id, Model model) 	throws PatientNotFoundException {
		Optional<Patient> patient = patientRepo.findById(id);

		if (patient.isPresent()) {
			model.addAttribute("patient", patient.get());

			Collection<Diary> diaryEntries = diaryRepo.findAllByPatient(patient.get());
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
		Optional<MedicalUser> medUser = medUserRepo.findById(id);

		if (medUser.isPresent()) {
				model.addAttribute("medicalUser", medUser.get());
				
				Patient patient = medUser.get().getPatient();
				model.addAttribute("patient", patient);
				
				NonMedicalUser nonMed = patient.getNonMedicalUser();				
				model.addAttribute("nonMedicalUser", nonMed);
				
				PatientStatus currentStatus= determineCurrentStatusOfPatientFromMedUser(medUser.get());
				model.addAttribute("status", currentStatus);
	
				Collection<Medication> medications = patient.getMedications();
				if (medications != null) {				
					checkForMissedDosesOnMedications(medications);
					model.addAttribute("medications", medications);
				}
			return "medicalUser-Home";
		}
		throw new MedicalUserNotFoundException();
	}
	

	@RequestMapping("/non-medical-user-home")
	public String returnNonMedicalUserHomePage(@RequestParam(value = "id") long id, Model model)
			throws NonMedicalUserNotFoundException {

		Optional<NonMedicalUser> nonMedUser = nonMedUserRepo.findById(id);

			if (nonMedUser.isPresent()) {
				model.addAttribute("nonMedicalUser", nonMedUser.get());
				
				PatientStatus currentStatus = determineCurrentStatusOfPatientFromNonMedUser(nonMedUser.get());
				model.addAttribute("status", currentStatus);
				
				Collection<Medication> medications = determineMedicationsFromNonMedUser(nonMedUser.get());				
				if (medications != null) {
					checkForMissedDosesOnMedications(medications);
					model.addAttribute("medications", medications);
				}

			return "nonMedicalUser-Home";
		}
		throw new NonMedicalUserNotFoundException();
	}
	
	private PatientStatus determineCurrentStatusOfPatientFromNonMedUser(NonMedicalUser nonMedUser) {
		Patient patient = nonMedUser.getPatient();
		Long patientId = patient.getId();
		return patientStatusRepo.findTop1ByPatientIdOrderByStatusDateTimeStampDesc(patientId);
	}

	private PatientStatus determineCurrentStatusOfPatientFromMedUser(MedicalUser medUser) {
		Patient patient = medUser.getPatient();
		Long patientId = patient.getId();
		return patientStatusRepo.findTop1ByPatientIdOrderByStatusDateTimeStampDesc(patientId);
	}

	private Collection<Medication> determineMedicationsFromNonMedUser(NonMedicalUser nonMedUser) {
		Patient patient = nonMedUser.getPatient();
		return patient.getMedications();
	}
	
	private void checkForMissedDosesOnMedications(Collection<Medication> medications) {			
		String currentMonthStart = calculateCurrentMonthStart();
		
		for (Medication medication : medications) {
			Collection<MedicationTracker> medicationTrackers = medTrackerRepo.findAllByMedicationAndDateGreaterThanEqual(medication, currentMonthStart);
			setAdherenceImageForMedication(medication, medicationTrackers);		
			medRepo.save(medication);
			}
	}
	
	private String calculateCurrentMonthStart() {
		DateTimeFormatter yyyymm = DateTimeFormatter.ofPattern("yyyy/MM");
		String thisMonth = LocalDateTime.now().format(yyyymm);
		return thisMonth + "/01";
	}
	
	private void setAdherenceImageForMedication(Medication medication, Collection<MedicationTracker> medicationTrackers) {
		if (calculateMissedDoses(medication, medicationTrackers)) {
			medication.setAdherentThisMonth("/images/false.png");
		} else {
			medication.setAdherentThisMonth("/images/true.png");
		}
	}
	
	private boolean calculateMissedDoses(Medication medication, Collection<MedicationTracker> medicationTrackers) {
		for (MedicationTracker medTracker : medicationTrackers) {
			if (medTracker.getDosesMissed() > 0
					&& medication.getFrequencyTime() != doseFrequencyTimeEnum.As_Needed) {
				return true; 
			}
		} 
		return false;
	}

	@RequestMapping("/my-medications")
	public String returnPatientMedications(@RequestParam(value = "id") long id, Model model)
			throws PatientNotFoundException {
		Optional<Patient> patient = patientRepo.findById(id);

		if (patient.isPresent()) {
			model.addAttribute("patient", patient.get());
						
			Collection<Medication> meds = patient.get().getMedications();
			model.addAttribute("medications", meds);
		
			Collection<MedicationTracker> medTrackers = determineAllTrackersForThisPatientMedicationsForToday(meds);
			model.addAttribute("medTrackers", medTrackers);
			return "my-Medications";
		}
		throw new PatientNotFoundException();
	}
	
	private Collection<MedicationTracker> determineAllTrackersForThisPatientMedicationsForToday(Collection<Medication> medications) {
		String today = calculateTodaysDate();
		Collection<MedicationTracker> medTrackers = new HashSet<>();
		
		for(Medication med : medications) {
			medTrackers.add(medTrackerRepo.findByMedicationAndDate(med, today));
		}
		return medTrackers;	
	}
	
	private String calculateTodaysDate() {
		DateTimeFormatter yyyymmdd = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		return LocalDateTime.now().format(yyyymmdd);
	}

	@RequestMapping("/med-tracker-history-non-med")
	public String returnMedTrackerHistoryNonMed(@RequestParam(value = "id") long id, Model model) throws Exception{

		Optional<Patient> patient = patientRepo.findById(id);
		if (patient.isPresent()) {
			model.addAttribute("patient", patient.get());

			NonMedicalUser nonMedUser = patient.get().getNonMedicalUser();
			model.addAttribute("nonMedicalUser", nonMedUser);
			
			Collection<Medication> medications = patient.get().getMedications();
			if (medications != null) {
				Collection<MedicationTracker> medicationTrackers = findAllTrackersForThisPatientMedicationsForThisYear(medications);
				
				if (medicationTrackers.size() > 0) {
					model.addAttribute("medicationTrackers", medicationTrackers);
				}
			}			
			return "med-Tracker-History-Non-Med";
		}
		throw new PatientNotFoundException();
	}
	
	@RequestMapping("/med-tracker-history-med")
	public String returnMedTrackerHistoryMedUser(@RequestParam(value = "id") long id, Model model) throws Exception {

		Optional<Patient> patient = patientRepo.findById(id);
		if (patient.isPresent()) {
			model.addAttribute("patient", patient.get());

			MedicalUser medUser = patient.get().getMedicalUser();
			model.addAttribute("medicalUser", medUser);

			Collection<Medication> medications = patient.get().getMedications();
			if (medications != null) {
				Collection<MedicationTracker> medicationTrackers = findAllTrackersForThisPatientMedicationsForThisYear(medications);
								
				if (medicationTrackers.size() > 0) {
					model.addAttribute("medicationTrackers", medicationTrackers);
				}
			}
			return "med-Tracker-History-Med";
		}
		throw new PatientNotFoundException();
	}
	
	private String calculateInitialDayOfThisYear() {
		DateTimeFormatter yyyy = DateTimeFormatter.ofPattern("yyyy");
		String currentYear = LocalDateTime.now().format(yyyy);
		return currentYear + "/01/01";
	}
	
	private Collection<MedicationTracker> findAllTrackersForThisPatientMedicationsForThisYear(Collection<Medication> medications) {
		String startDate = calculateInitialDayOfThisYear();
		Collection<MedicationTracker> medicationTrackers = new HashSet<>();	
		for (Medication medication : medications) {
			medicationTrackers
			.addAll(medTrackerRepo.findAllByMedicationAndDateGreaterThanEqual(medication, startDate));
		}
		return medicationTrackers;
	}
		
	@RequestMapping("/user-login")
	public String userLogin() {
		return "/user-login";
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String Login(String userName, String password, Model model) {
		MedicalUser medUser = medUserRepo.findByUserNameAndPassword(userName, password);
		NonMedicalUser nonMedUser = nonMedUserRepo.findByUserNameAndPassword(userName, password);
		Patient patient = patientRepo.findByUserNameAndPassword(userName, password);
		
		if (medUser != null) {
			long id = medUser.getId();
			
			return "redirect:/medical-user-home?id=" + id;
		} else if (nonMedUser != null) {
			long id = nonMedUser.getId();
			
			return "redirect:/non-medical-user-home?id=" + id;
		} else if (patient != null) {
			
			long id = patient.getId();
			return "redirect:/patient-home?id=" + id;
		} else {

		return "redirect:/user-login";
		}
	}

}
