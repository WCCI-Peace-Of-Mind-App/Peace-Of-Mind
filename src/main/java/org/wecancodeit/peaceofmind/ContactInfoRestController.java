package org.wecancodeit.peaceofmind;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show-contact-info")
public class ContactInfoRestController {
	
	@Resource
	private ContactInfoRepository contactRepo; 
	
	@Resource
	private EmailRepository emailRepo;
	
	@Resource
	private AddressRepository addyRepo;
	
	@Resource
	private PhoneRepository phoneRepo;
	
	@RequestMapping("")
	public Iterable<ContactInfo> findAllContactInfo() {
		return contactRepo.findAll(); 
	}
	
	@RequestMapping("/{id}")
	public Optional<ContactInfo> findOneContact(@PathVariable long id){
		return contactRepo.findById(id);
	}
	
	
}
