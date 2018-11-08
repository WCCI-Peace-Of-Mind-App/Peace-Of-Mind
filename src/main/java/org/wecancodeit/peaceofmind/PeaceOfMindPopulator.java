package org.wecancodeit.peaceofmind;

import java.util.ArrayList;
import java.util.Collection;

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
		Address address1b = addressRepo.save(new Address("4260 Tuller Ridge Drive", null, "Dublin", "OH", "43017", "work"));
		
		Phone phone1a = phoneRepo.save(new Phone("614-123-4567", "home"));
		Phone phone1b = phoneRepo.save(new Phone("614-987-6544", "work"));
		
		Email email1a = emailRepo.save(new Email("123@abd.com", "home"));
		
		ContactInfo contactInfo1 = contactInfoRepo.save(new ContactInfo());
		contactInfo1.addAddress(address1a);
		contactInfo1.addAddress(address1b);
		contactInfo1.addPhone(phone1a);
		contactInfo1.addPhone(phone1b);
		contactInfo1.addEmail(email1a);
		
		Patient patient1 = patientRepo.save(new Patient("Joe", "Bob", contactInfo1, "01/01/01", "Alzheimers"));
		
		// End Patient 1 build
		
		// Non Med User 2 build

		Address address2a = addressRepo.save(new Address("123 Builder St", null, "Columbus", "Ohio", "43055", "home"));
		Address address2b = addressRepo.save(new Address("4111 Tuller Road", null, "Dublin", "OH", "43017", "work"));
		
		Phone phone2a = phoneRepo.save(new Phone("614-555-4321", "home"));
		
		Email email2a = emailRepo.save(new Email("321@zyx.com", "work"));
		Email email2b = emailRepo.save(new Email("xxGAMERxx@yahoo.com", "home"));
		
		ContactInfo contactInfo2 = contactInfoRepo.save(new ContactInfo());
		contactInfo2.addAddress(address2a);
		contactInfo2.addAddress(address2b);
		contactInfo2.addPhone(phone2a);
		contactInfo2.addEmail(email2a);
		contactInfo2.addEmail(email2b);
		
		
		NonMedicalUser nonMedUser2 = nonMedUserRepo.save(new NonMedicalUser("Jenny", "Wilson", contactInfo2, "xxGAMERxx", "pass1234", "Daughter"));
		
		//end NonMedUser 2
		
		//begin MedUser 3
		Address address3a = addressRepo.save(new Address("555 Candlestick Court", null, "Obetz", "Ohio", "43207", "home"));
		
		Phone phone3a = phoneRepo.save(new Phone("614-777-6464", "home"));
		Phone phone3b = phoneRepo.save(new Phone("614-911-9110", "work"));
		
		Email email3a = emailRepo.save(new Email("docOc@osu.com", "work"));
		Email email3b = emailRepo.save(new Email("octoDad@gmail.com", "home"));
		
		ContactInfo contactInfo3 = contactInfoRepo.save(new ContactInfo());
		contactInfo3.addAddress(address3a);
		contactInfo3.addPhone(phone3a);
		contactInfo3.addPhone(phone3b);
		contactInfo3.addEmail(email3b);
		contactInfo3.addEmail(email3a);
		
		
		MedicalUser medUser3 = medUserRepo.save(new MedicalUser("Otto", "Octavius", contactInfo3, "Therapist", "Ohio State Med", "911", "docOc", "tentacles8"));
	}

}
