package org.wecancodeit.peaceofmind;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Controller
public class PeaceOfMindPostController {

	@Resource
	private AddressRepository addressRepo;
	
	@Resource
	private PhoneRepository phoneRepo;
	
	@Resource
	private EmailRepository emailRepo;
	
	@Resource
	private ContactInfoRepository contactInfoRepo;
	
	public Address addAddress(String streetAddress, String secondaryField, String city, String state, String zipCode,
			String type) {
		Address address = new Address(streetAddress, secondaryField, city, state, zipCode, type);
		addressRepo.save(address);
		return address;
	}


	public Phone addPhone(String phoneNumber, String type) {
		Phone phone = new Phone(phoneNumber, type);
		phoneRepo.save(phone);
		return phone;
	}


	public Email addEmail(String emailAddress, String type) {
		Email email = new Email(emailAddress, type);
		emailRepo.save(email);
		return email;
	}


	public void addContactInfo(String streetAddress, String secondaryField, String city, String state, String zipCode,
			String aType, String phoneNumber, String pType, String emailAddress, String eType) {
		Address address = addAddress(streetAddress, secondaryField, city, state, zipCode, aType);
		Phone phone = addPhone(phoneNumber, pType);
		Email email = addEmail(emailAddress, eType);
		ContactInfo contactInfo = new ContactInfo(address, email, phone);
		contactInfoRepo.save(contactInfo);
		
	}

}
