package org.wecancodeit.peaceofmind;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PeaceOfMindPostControllerTest {
	
	@InjectMocks
	private PeaceOfMindPostController underTest;
	
	@Mock
	private AddressRepository addressRepo;
	
	@Mock
	private PhoneRepository phoneRepo;
	
	@Mock
	private EmailRepository emailRepo;
	
	@Mock
	private ContactInfoRepository contactInfoRepo;
	
	@Mock
	private NonMedicalUserRepository nonMedUserRepo;
	
	@Mock
	private PatientRepository patientRepo;
	
	@Mock
	private MedicalUserRepository medUserRepo;
	
	String streetAddress;
	String secondaryField;
	String city;
	String state;
	String zipCode;
	String aType;
	
	String phoneNumber;
	String pType;
	
	String emailAddress;
	String eType;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		streetAddress = "123 Main St.";
		secondaryField = "Apt 111";
		city = "Dublin";
		state = "OH";
		zipCode = "43101";
		aType = "home";
		
		phoneNumber = "614-837-5609";
		pType = "home";
		
		emailAddress = "123@xyz.com";
		eType = "home";
	}
		
	@Test
	public void shouldAddSingleContactInfoToModelAndVerifyEachContactMethod() throws Exception {	
		underTest.addContactInfo(streetAddress, secondaryField, city, state, zipCode, aType, phoneNumber, pType, emailAddress, eType);
		
		ArgumentCaptor<ContactInfo> contactInfoArgument = ArgumentCaptor.forClass(ContactInfo.class);
		verify(contactInfoRepo).save(contactInfoArgument.capture());
		
		ArgumentCaptor<Address> addressArgument = ArgumentCaptor.forClass(Address.class);
		verify(addressRepo).save(addressArgument.capture());
		assertEquals(streetAddress, addressArgument.getValue().getStreetAddress());

		ArgumentCaptor<Phone> phoneArgument = ArgumentCaptor.forClass(Phone.class);
		verify(phoneRepo).save(phoneArgument.capture());
		assertEquals(phoneNumber, phoneArgument.getValue().getPhoneNumber());

		ArgumentCaptor<Email> emailArgument = ArgumentCaptor.forClass(Email.class);
		verify(emailRepo).save(emailArgument.capture());
		assertEquals(emailAddress, emailArgument.getValue().getEmailAddress());
	}
	
	@Test
	public void shouldAddSingleNonMedUserToModel() throws Exception {
		String firstName = "Joe";
		String lastName = "Bob";
		String username = "mainman";
		String password = "l33tus3r";
		String relationshipToPatient = "son";
		
		underTest.addNonMedicalUser(firstName, lastName, username, password, relationshipToPatient, streetAddress, secondaryField, city, state, zipCode, aType, phoneNumber, pType, emailAddress, eType);
		
		ArgumentCaptor<NonMedicalUser> nonMedUserArgument = ArgumentCaptor.forClass(NonMedicalUser.class);
		verify(nonMedUserRepo).save(nonMedUserArgument.capture());
		assertEquals(firstName, nonMedUserArgument.getValue().getFirstName());
	}
	
	@Test
	public void shouldAddPatientToModel() throws Exception {
		NonMedicalUser nonMedUser = nonMedUserRepo.save(new NonMedicalUser("Joe", "Bob", null, "xx", "yy", "son"));
		
		String firstName = "Jen";
		String lastName = "Senn";
		String dateOfBirth = "01/01/1951";
		String diagnosis = "alzh";
		
		underTest.addPatient(firstName, lastName, dateOfBirth, diagnosis, nonMedUser, streetAddress, secondaryField, city, state, zipCode, aType, phoneNumber, pType, emailAddress, eType);
		
		ArgumentCaptor<Patient> patientArgument = ArgumentCaptor.forClass(Patient.class);
		verify(patientRepo).save(patientArgument.capture());
		assertEquals(firstName, patientArgument.getValue().getFirstName());
	}
	
	@Test
	public void shouldAddMedicalUserToModel() throws Exception {
		Patient patient = patientRepo.save(new Patient("Jen", "Senn", null, "01/01/1951", "alzh", null));
		
		String firstName = "Doc";
		String lastName = "Oc";
		String medicalSpecialty = "therapist";
		String medicalInstitution = "OSU";
		String institutionTelephone = "911";
		String userName = "parker";
		String password = "p4rk3r";

		underTest.addMedicalUser(firstName, lastName, medicalSpecialty, medicalInstitution, institutionTelephone, userName, password, patient, streetAddress, secondaryField, city, state, zipCode, aType, phoneNumber, pType, emailAddress, eType);
		
		ArgumentCaptor<MedicalUser> medUserArgument = ArgumentCaptor.forClass(MedicalUser.class);
		verify(medUserRepo).save(medUserArgument.capture());
		assertEquals(firstName, medUserArgument.getValue().getFirstName());
	}

}
