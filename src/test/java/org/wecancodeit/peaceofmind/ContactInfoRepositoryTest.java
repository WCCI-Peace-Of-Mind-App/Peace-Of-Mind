package org.wecancodeit.peaceofmind;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class ContactInfoRepositoryTest {
	
	@Resource
	private ContactInfoRepository contactInfoRepo;

	
	@Resource
	private TestEntityManager entityManager;	
	
	@Resource 
	private PatientRepository patientRepo;
	
	@Resource
	private NonMedicalUserRepository nonMedUserRepo;
	
	@Resource
	private MedicalUserRepository medicalUserRepo;
	
	@Resource
	private AddressRepository addressRepo;
	
	@Resource
	private PhoneRepository phoneRepo;
	
	@Resource
	private EmailRepository emailRepo;
	
	ContactInfo contact1;
	Address address;
	Phone phone;
	Email email;
	long contactId;
	
	Patient patient;
	NonMedicalUser nonMedUser;
	MedicalUser medicalUser;
	
	ContactInfo testContact;
		
	@Before
	public void setUp() {
		contact1 = contactInfoRepo.save(new ContactInfo());

		address = addressRepo.save(new Address("", "", "", "", "", ""));
		phone = phoneRepo.save(new Phone("", ""));
		email = emailRepo.save(new Email("", ""));

		contactId = contact1.getId();
		
		contact1.addAddress(address);
		contact1.addPhone(phone);
		contact1.addEmail(email);
		
		
		patient = patientRepo.save(new Patient("", "", contact1, "", ""));
		nonMedUser = nonMedUserRepo.save(new NonMedicalUser("", "", contact1, "", "", ""));
		medicalUser = medicalUserRepo.save(new MedicalUser("", "", contact1, "", "", "", "", ""));
		
		entityManager.flush();
		entityManager.clear();


		
		Optional<ContactInfo> underTest = contactInfoRepo.findById(contactId);
		testContact = underTest.get();
	}
	
	@Test
	public void shouldBeAnInstanceofContactInfo() {
		assertThat(contact1, instanceOf(ContactInfo.class));
	}
	
	@Test
	public void shouldVerifyContact1HasIdGreaterThan0L() {
		assertThat(contactId, is(greaterThan(0L)));
	}

	@Test
	public void shouldValidateContactHasAHomeAddress() {
		assertThat(testContact.getAddresses(), contains(address));
	}
	
	@Test
	public void shouldValidateContactHasAHomePhone() {
		assertThat(testContact.getPhones(), contains(phone));
	}
	
	@Test
	public void shouldValidateContactHasAnEmail() {
		assertThat(testContact.getEmails(), contains(email));
	}
	
	@Test
	public void shouldEstablishRelationshipfromContactInfoToPatient() {
		assertThat(testContact.getPatient(), is(patient));
	}
		
	@Test
	public void shouldEstablishRelationshipFromContactInfoToNonMedUser() {
		assertThat(testContact.getNonMedicalUser(), is(nonMedUser));
	}
	
	@Test
	public void shouldEstablishRelationshipFromContactInfoToMedicalUser() {
		assertThat(testContact.getMedicalUser(), is(medicalUser));
	}
}
