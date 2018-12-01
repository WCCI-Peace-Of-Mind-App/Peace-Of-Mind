package org.wecancodeit.peaceofmind;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PeaceOfMindPopulator implements CommandLineRunner {


	@Resource
	private AddressRepository addressRepo;
	
	@Resource
	private PhoneRepository phoneRepo;
		
	@Resource
	private EmailRepository emailRepo;
	
	@Resource
	private ContactInfoRepository contactInfoRepo;

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
	private MedicationTrackerRepository medTrackerRepo;
  
  @Resource
  private PatientStatusRepository patientStatusRepo;
	
	DateTimeFormatter yyyymmddhhmm = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"); 
	String pastDate = LocalDateTime.now().minusDays(2).format(yyyymmddhhmm);
	
	
	@Override
	public void run(String... args) throws Exception {

		// Non Med User 2 build
		
		Address address2a = addressRepo.save(new Address("123 Builder St", null, "Columbus", "Ohio", "43055", "home"));
		
		Phone phone2a = phoneRepo.save(new Phone("614-555-4321", "home"));
		
		Email email2a = emailRepo.save(new Email("321@zyx.com", "work"));
		
		
		ContactInfo contactInfo2 = contactInfoRepo.save(new ContactInfo(address2a, email2a, phone2a));
		
		
		NonMedicalUser nonMedUser2 = nonMedUserRepo.save(new NonMedicalUser("Jenny", "Wilson", contactInfo2, "xxGAMERxx", "pass1234", "Daughter"));
		
		//end NonMedUser 2

		// Patient 1 build
		
		Address address1a = addressRepo.save(new Address("123 Baker St", null, "Columbus", "Ohio", "43081", "home"));
		
		Phone phone1a = phoneRepo.save(new Phone("614-123-4567", "home"));
		
		Email email1a = emailRepo.save(new Email("123@abd.com", "home"));
		
		ContactInfo contactInfo1 = contactInfoRepo.save(new ContactInfo(address1a, email1a, phone1a));
		
		Medication med1 = medRepo.save(new Medication("Donepezil", "23mg", "Oral", 1, "Daily", "https://rxpillimage.nlm.nih.gov/RxImage/image/images/gallery/original/45802-0571-78_NLMIMAGE10_7008B865.jpg", "Senile Dementia"));
		Medication med2 = medRepo.save(new Medication("Sumatriptan", "100mg", "Oral", 0, "As needed", "https://rxpillimage.nlm.nih.gov/RxImage/image/images/gallery/original/00378-4532-01_NLMIMAGE10_613F30B9.jpg", "Migraines"));
    Medication med3 = medRepo.save(new Medication("Aricept", "5mg", "Oral", 1, "Daily, with evening meal", "https://rxpillimage.nlm.nih.gov/RxImage/image/images/gallery/original/00143-3142-05_NLMIMAGE10_E005702B.jpg", "Senile Dementia"));
    Medication med4 = medRepo.save(new Medication("Galantamine", "4mg", "Oral", 1, "Daily, with morning meal", "https://rxpillimage.nlm.nih.gov/RxImage/image/images/gallery/original/00591-5440-50_NLMIMAGE10_B834DC76.jpg", "Senile Dementia"));
	  Medication med5 = medRepo.save(new Medication("Memantine", "5mg", "Oral", 1, "Daily, with evening meal", "https://rxpillimage.nlm.nih.gov/RxImage/image/images/gallery/original/00378-3030-01_NLMIMAGE10_093984BC.jpg", "Senile Dementia"));
    Medication med6 = medRepo.save(new Medication("Donepezil", "5mg", "Oral", 1, "Daily, before sleep", "https://rxpillimage.nlm.nih.gov/RxImage/image/images/gallery/original/00143-3142-05_NLMIMAGE10_E005702B.jpg", "Senile Dementia"));
    Medication med7 = medRepo.save(new Medication("Ondansetron", "8mg", "Oral", 0, "As needed","https://rxpillimage.nlm.nih.gov/RxImage/image/images/gallery/original/00591-5553-50_NLMIMAGE10_DD346E93.jpg", "Nausea"));
    Medication med8 = medRepo.save(new Medication("Promethazine", "25mg", "Oral", 0, "As needed", "https://rxpillimage.nlm.nih.gov/RxImage/image/images/gallery/original/00591-5440-50_NLMIMAGE10_B834DC76.jpg", "Nausea"));
    Medication med9 = medRepo.save(new Medication("Metoclopramide", "10mg", "Oral", 0, "As needed", "https://rxpillimage.nlm.nih.gov/RxImage/image/images/gallery/original/00591-5535-50_NLMIMAGE10_331599CC.jpg", "Nausea"));
    Medication med10 = medRepo.save(new Medication("Oxcarbazepine", "300mg", "Oral", 3, "Daily", "https://rxpillimage.nlm.nih.gov/RxImage/image/images/gallery/original/00591-5553-50_NLMIMAGE10_DD346E93.jpg", "Anti-Convulsant"));
    Medication med11 = medRepo.save(new Medication("Phenytoin", "30mg", "Oral", 1, "Daily, before sleep", "https://rxpillimage.nlm.nih.gov/RxImage/image/images/gallery/original/00591-5553-50_NLMIMAGE10_DD346E93.jpg", "Anti-Convulsant"));
    Medication med12 = medRepo.save(new Medication("Gabapentin", "800mg", "Oral", 2, "Daily","https://rxpillimage.nlm.nih.gov/RxImage/image/images/gallery/original/00591-5535-50_NLMIMAGE10_331599CC.jpg", "Anti-Convulsant"));
    Medication med13 = medRepo.save(new Medication("Amlodipine", "5mg", "Oral", 1, "Daily", "https://rxpillimage.nlm.nih.gov/RxImage/image/images/gallery/original/00093-0058-01_NLMIMAGE10_00280060.jpg", "Anti-Hypertensive"));
    Medication med14 = medRepo.save(new Medication("Losartan", "50mg", "Oral", 1, "Daily", "https://rxpillimage.nlm.nih.gov/RxImage/image/images/gallery/original/00378-4151-01_NLMIMAGE10_2A21954C.jpg", "Anti-Hypertensive"));
    Medication med15 = medRepo.save(new Medication("Lisinopril", "10mg", "Oral", 1, "Daily", "https://rxpillimage.nlm.nih.gov/RxImage/image/images/gallery/original/00603-0026-32_NLMIMAGE10_2F4717B8.jpg", "Anti-Hypertensive"));
    Medication med16 = medRepo.save(new Medication("Hydrochlorothiazide", "12.5mg", "Oral", 1, "Daily", "https://rxpillimage.nlm.nih.gov/RxImage/image/images/gallery/original/00536-3086-10_NLMIMAGE10_4F3A27D1.jpg", "Diuretic"));
    Medication med17 = medRepo.save(new Medication("Furosemide", "20mg", "Oral", 1, "Daily", "https://rxpillimage.nlm.nih.gov/RxImage/image/images/gallery/original/00904-2013-72_NLMIMAGE10_A34ED1E6.jpg", "Diuretic"));
    Medication med18 = medRepo.save(new Medication("Spironolactone", "50mg", "Oral", 2, "Daily, morning and evening", "https://rxpillimage.nlm.nih.gov/RxImage/image/images/gallery/original/00536-1004-10_NLMIMAGE10_1A400D30.jpg","Diuretic"));

		
		Patient patient1 = patientRepo.save(new Patient("Joe", "Bob", contactInfo1, "01/01/01", "Alzheimers", nonMedUser2, med1, med2));
		medLogRepo.save(new MedicationLog(med1));
		medLogRepo.save(new MedicationLog(med2));
		medLogRepo.save(new MedicationLog(med1, pastDate));
		medTrackerRepo.save(new MedicationTracker(med1));
		medTrackerRepo.save(new MedicationTracker(med2));
		medLogRepo.save(new MedicationLog(med1));
		medLogRepo.save(new MedicationLog(med1));

		
        patientStatusRepo.save(new PatientStatus(PatientStatusEnum.WELL, patient1));
        patientStatusRepo.save(new PatientStatus(PatientStatusEnum.NOTWELL, patient1));

		// End Patient 1 build
		
		
		//begin MedUser 3
		Address address3a = addressRepo.save(new Address("555 Candlestick Court", null, "Obetz", "Ohio", "43207", "home"));
		
		Phone phone3a = phoneRepo.save(new Phone("614-777-6464", "home"));
		
		Email email3a = emailRepo.save(new Email("docOc@osu.com", "work"));
		
		ContactInfo contactInfo3 = contactInfoRepo.save(new ContactInfo(address3a, email3a, phone3a));
		
		
		medUserRepo.save(new MedicalUser("Otto", "Octavius", contactInfo3, "Therapist", "Ohio State Med", "911", "docOc", "tentacles8", patient1));
		
		

	}

}
