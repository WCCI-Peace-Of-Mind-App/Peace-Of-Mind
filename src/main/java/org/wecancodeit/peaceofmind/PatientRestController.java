package org.wecancodeit.peaceofmind;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
@RequestMapping("/show-patients")
public class PatientRestController {
	
	@Resource
	private PatientRepository patientRepo;

@Resource
private MedicationLogRepository medicationLogRepo;
	
	@RequestMapping("")
	public Iterable<Patient> findAllPatients() {
		return patientRepo.findAll();
	}

  @RequestMapping("/{patientId}/patient-medication-log-update")
  public String addPatientMedicationLog(
      @PathVariable(value="patientId") long patientId, @RequestParam(value="medicationId") Long medicationId, Model model
      ) throws PatientNotFoundException, MedicationNotFoundException{
    Optional<Patient> oQueriedPatient = patientRepo.findById(patientId);
    if(oQueriedPatient.isPresent()) {
      Collection<Medication> oMedicationCollection = oQueriedPatient.get().getMedications();
      for (Medication medication : oMedicationCollection) {
        if(new Long(medication.getId()).equals(medicationId)){
          medicationLogRepo.save(new MedicationLog(medication));
          return "partials/patient-medication-log-update.html";
        }
      }
    } else throw new MedicationNotFoundException();
    throw new PatientNotFoundException();
  }
}
