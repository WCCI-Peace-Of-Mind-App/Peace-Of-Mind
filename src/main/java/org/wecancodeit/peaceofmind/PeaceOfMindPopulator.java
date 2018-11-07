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
		
		Collection<Address> addresses1 = new ArrayList<>();
		Address address1a = addressRepo.save(new Address("123 Baker St", null, "Columbus", "Ohio", "43081", "home"));
		Address address1b = addressRepo.save(new Address("4260 Tuller Ridge Drive", null, "Dublin", "OH", "43017", "work"));
		
		addresses1.add(address1a);
		addresses1.add(address1b);
		
		Collection<Phone> phones1 = new ArrayList<>();
		Phone phone1a = phoneRepo.save(new Phone("614-123-4567", "home"));
		Phone phone1b = phoneRepo.save(new Phone("614-987-6544", "work"));
		phones1.add(phone1a);
		phones1.add(phone1b);
		
		Collection<Email> emails1 = new ArrayList<>();
		Email email1a = emailRepo.save(new Email("123@abd.com", "home"));
		emails1.add(email1a);
		
		ContactInfo contactInfo1 = contactInfoRepo.save(new ContactInfo(addresses1, phones1, emails1));
		
		Patient patient1 = patientRepo.save(new Patient("Joe", "Bob", contactInfo1, "01/01/01", "Alzheimers"));
		
		// End Patient 1 build
		
		// Non Med User 2 build

		Collection<Address> addresses2 = new ArrayList<>();
		Address address2a = addressRepo.save(new Address("123 Builder St", null, "Columbus", "Ohio", "43055", "home"));
		Address address2b = addressRepo.save(new Address("4111 Tuller Road", null, "Dublin", "OH", "43017", "work"));
		
		addresses2.add(address2a);
		addresses2.add(address2b);
		
		Collection<Phone> phones2 = new ArrayList<>();
		Phone phone2a = phoneRepo.save(new Phone("614-555-4321", "home"));
		phones2.add(phone2a);
		
		Collection<Email> emails2 = new ArrayList<>();
		Email email2a = emailRepo.save(new Email("321@zyx.com", "work"));
		Email email2b = emailRepo.save(new Email("xxGAMERxx@yahoo.com", "home"));
		emails2.add(email2a);
		emails2.add(email2b);
		
		ContactInfo contactInfo2 = contactInfoRepo.save(new ContactInfo(addresses2, phones2, emails2));
		
		NonMedicalUser nonMedUser2 = nonMedUserRepo.save(new NonMedicalUser("Jenny", "Wilson", contactInfo2, "xxGAMERxx", "pass1234", "Daughter"));
		
		//end NonMedUser 2
		
		//begin MedUser 3
		Collection<Address> addresses3 = new ArrayList<>();
		Address address3a = addressRepo.save(new Address("555 Candlestick Court", null, "Obetz", "Ohio", "43207", "home"));
		
		addresses3.add(address3a);
		
		Collection<Phone> phones3 = new ArrayList<>();
		Phone phone3a = phoneRepo.save(new Phone("614-777-6464", "home"));
		Phone phone3b = phoneRepo.save(new Phone("614-911-9110", "work"));
		phones3.add(phone3a);
		phones3.add(phone3b);
		
		Collection<Email> emails3 = new ArrayList<>();
		Email email3a = emailRepo.save(new Email("docOc@osu.com", "work"));
		Email email3b = emailRepo.save(new Email("octoDad@gmail.com", "home"));
		emails3.add(email3a);
		emails3.add(email3b);
		
		ContactInfo contactInfo3 = contactInfoRepo.save(new ContactInfo(addresses3, phones3, emails3));

		MedicalUser medUser3 = medUserRepo.save(new MedicalUser("Otto", "Octavius", contactInfo3, "Therapist", "Ohio State Med", "911", "docOc", "tentacles8"));
	}

}
