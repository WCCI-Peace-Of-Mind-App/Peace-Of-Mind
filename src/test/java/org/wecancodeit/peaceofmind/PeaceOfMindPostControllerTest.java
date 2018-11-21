package org.wecancodeit.peaceofmind;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

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
	private Model model;
	
	long arbitraryId = 1;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddSingleAddressToModel() throws Exception {
		String streetAddress = "123 Main St.";
		String secondaryField = "Apt 111";
		String city = "Dublin";
		String state = "OH";
		String zipCode = "43101";
		String type = "home";
		
		underTest.addAddress(streetAddress,secondaryField, city, state, zipCode, type);
		
		ArgumentCaptor<Address> addressArgument = ArgumentCaptor.forClass(Address.class);
		verify(addressRepo).save(addressArgument.capture());
		assertEquals(streetAddress, addressArgument.getValue().getStreetAddress());
	}
	
	@Test
	public void shouldAddSinglePhoneToModel() throws Exception {
		String phoneNumber = "614-837-5609";
		String type = "home";
		
		underTest.addPhone(phoneNumber, type);
		
		ArgumentCaptor<Phone> phoneArgument = ArgumentCaptor.forClass(Phone.class);
		verify(phoneRepo).save(phoneArgument.capture());
		assertEquals(phoneNumber, phoneArgument.getValue().getPhoneNumber());
	}
	
	@Test
	public void shouldAddSingleEmailToModel() throws Exception {
		String emailAddress = "123@xyz.com";
		String type = "home";
		
		underTest.addEmail(emailAddress, type);
		
		ArgumentCaptor<Email> emailArgument = ArgumentCaptor.forClass(Email.class);
		verify(emailRepo).save(emailArgument.capture());
		assertEquals(emailAddress, emailArgument.getValue().getEmailAddress());
	}
	
	@Test
	public void shouldAddSingleContactInfoToModel() throws Exception {
		String streetAddress = "123 Main St.";
		String secondaryField = "Apt 111";
		String city = "Dublin";
		String state = "OH";
		String zipCode = "43101";
		String aType = "home";
		
		String phoneNumber = "614-837-5609";
		String pType = "home";
		
		String emailAddress = "123@xyz.com";
		String eType = "home";
		
		underTest.addContactInfo(streetAddress, secondaryField, city, state, zipCode, aType, phoneNumber, pType, emailAddress, eType);
		
		ArgumentCaptor<ContactInfo> contactInfoArgument = ArgumentCaptor.forClass(ContactInfo.class);
		verify(contactInfoRepo).save(contactInfoArgument.capture());
		
	}
	
	@Test
	public void shouldAddSingleNonMedUserToModel() throws Exception {
		
		String streetAddress = "123 Main St.";
		String secondaryField = "Apt 111";
		String city = "Dublin";
		String state = "OH";
		String zipCode = "43101";
		String aType = "home";
		
		String phoneNumber = "614-837-5609";
		String pType = "home";
		
		String emailAddress = "123@xyz.com";
		String eType = "home";
		
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

}
