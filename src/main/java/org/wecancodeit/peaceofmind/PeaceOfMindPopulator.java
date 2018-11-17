package org.wecancodeit.peaceofmind;

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
	
	
	
	
	@Override
	public void run(String... args) throws Exception {

		// Patient 1 build
		
		Address address1a = addressRepo.save(new Address("123 Baker St", null, "Columbus", "Ohio", "43081", "home"));
		
		Phone phone1a = phoneRepo.save(new Phone("614-123-4567", "home"));
		
		Email email1a = emailRepo.save(new Email("123@abd.com", "home"));
		
		ContactInfo contactInfo1 = contactInfoRepo.save(new ContactInfo(address1a, email1a, phone1a));
		
		phoneRepo.save(phone1a);
		
		addressRepo.save(address1a);
		
		emailRepo.save(email1a);
		
		
		
		patientRepo.save(new Patient("Joe", "Bob", contactInfo1, "01/01/01", "Alzheimers"));
		
		// End Patient 1 build
		
		// Non Med User 2 build

		Address address2a = addressRepo.save(new Address("123 Builder St", null, "Columbus", "Ohio", "43055", "home"));
		
		Phone phone2a = phoneRepo.save(new Phone("614-555-4321", "home"));
		
		Email email2a = emailRepo.save(new Email("321@zyx.com", "work"));

		
		ContactInfo contactInfo2 = contactInfoRepo.save(new ContactInfo(address2a, email2a, phone2a));

		
		nonMedUserRepo.save(new NonMedicalUser("Jenny", "Wilson", contactInfo2, "xxGAMERxx", "pass1234", "Daughter"));
		
		//end NonMedUser 2
		
		//begin MedUser 3
		Address address3a = addressRepo.save(new Address("555 Candlestick Court", null, "Obetz", "Ohio", "43207", "home"));
		
		Phone phone3a = phoneRepo.save(new Phone("614-777-6464", "home"));
		
		Email email3a = emailRepo.save(new Email("docOc@osu.com", "work"));
		
		ContactInfo contactInfo3 = contactInfoRepo.save(new ContactInfo(address3a, email3a, phone3a));
		
		
		medUserRepo.save(new MedicalUser("Otto", "Octavius", contactInfo3, "Therapist", "Ohio State Med", "911", "docOc", "tentacles8"));
		

	}

}