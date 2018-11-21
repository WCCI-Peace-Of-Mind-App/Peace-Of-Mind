package org.wecancodeit.peaceofmind;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Controller
public class PeaceOfMindPostController {

	@Resource
	private MedicalUserRepository medUserRepo;
	
	@Resource
	private PatientRepository patientRepo;
	
	@Resource
	private NonMedicalUserRepository nonMedUserRepo;

	@Resource
	private ContactInfoRepository contactInfoRepo;
	
	@Resource
	private AddressRepository addressRepo;
	
	@Resource
	private EmailRepository emailRepo;
	
	@Resource
	private PhoneRepository phoneRepo;
	
	
	public void addMedicalUser(String firstName, String lastName, String medicalSpecialty, String medicalInstitution,
			String institutionTelephone, String userName, String password, Patient patient, String streetAddress,
			String secondaryField, String city, String state, String zipCode, String aType, String phoneNumber,
			String pType, String emailAddress, String eType) {
		ContactInfo contactInfo = addContactInfo(streetAddress, secondaryField, city, state, zipCode, aType, phoneNumber, pType, emailAddress, eType);
		MedicalUser medUser = new MedicalUser(firstName, lastName, contactInfo, medicalSpecialty, medicalInstitution, institutionTelephone, userName, password, patient);
		medUserRepo.save(medUser);
	}

	public void addPatient(String firstName, String lastName, String dateOfBirth, String diagnosis,
			NonMedicalUser nonMedUser, String streetAddress, String secondaryField, String city, String state,
			String zipCode, String aType, String phoneNumber, String pType, String emailAddress, String eType) {
		ContactInfo contactInfo = addContactInfo(streetAddress, secondaryField, city, state, zipCode, aType, phoneNumber, pType, emailAddress, eType);
		Patient patient = new Patient(firstName, lastName, contactInfo, dateOfBirth, diagnosis, nonMedUser);
		patientRepo.save(patient);
	}
	
	public void addNonMedicalUser(String firstName, String lastName, String userName, String password,
			String relationshipWithPatient, String streetAddress, String secondaryField, String city, String state,
			String zipCode, String aType, String phoneNumber, String pType, String emailAddress, String eType) {
		ContactInfo contactInfo = addContactInfo(streetAddress, secondaryField, city, state, zipCode, aType, phoneNumber, pType, emailAddress, eType);
		NonMedicalUser nonMedUser = new NonMedicalUser(firstName, lastName, contactInfo, userName, password, relationshipWithPatient);
		nonMedUserRepo.save(nonMedUser);
		
	}

	public ContactInfo addContactInfo(String streetAddress, String secondaryField, String city, String state, String zipCode,
			String aType, String phoneNumber, String pType, String emailAddress, String eType) {
		Address address = addAddress(streetAddress, secondaryField, city, state, zipCode, aType);
		Phone phone = addPhone(phoneNumber, pType);
		Email email = addEmail(emailAddress, eType);
		ContactInfo contactInfo = new ContactInfo(address, email, phone);
		contactInfoRepo.save(contactInfo);
		return contactInfo;
	}

	public Address addAddress(String streetAddress, String secondaryField, String city, String state, String zipCode,
			String type) {
		Address address = new Address(streetAddress, secondaryField, city, state, zipCode, type);
		addressRepo.save(address);
		return address;
	}
		
	public Email addEmail(String emailAddress, String type) {
		Email email = new Email(emailAddress, type);
		emailRepo.save(email);
		return email;
	}

	public Phone addPhone(String phoneNumber, String type) {
		Phone phone = new Phone(phoneNumber, type);
		phoneRepo.save(phone);
		return phone;
	}



}
