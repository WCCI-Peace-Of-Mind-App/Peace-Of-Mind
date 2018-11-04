package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

public class MedicalUserTest {
	
	String address;
	String businessTelephone;
	
	Collection<String> addresses = new ArrayList<>();
	Collection<String> phones = new ArrayList<>();
	
	
	ContactInfo contactInfo;
	
	Person user;
	String firstName;
	String lastName;
	String medicalSpecialty;
	String medInstitution;
	String institutionTelephone;
	String userName;
	String userPassword;
	
	//String name, String address, ContactInfo contactInfo, String medicalSpecialty, String medicalInstitution, String institutionTelephone, String userName, String password	
	Person user2;
	
	@Before
	public void setUp() {
		address = "WCCI_CleveLand";
		businessTelephone = "7402442444";
		
		addresses.add(address);
		phones.add(businessTelephone);
		contactInfo = new ContactInfo(addresses, phones, null);
			
		firstName = "Michael";
		lastName = "Bob";
		medicalSpecialty = "Therapist";
		medInstitution = "MtCarmel";
		institutionTelephone = "8009999990";
		userName = "WcciAdmin2";
		userPassword = "1999";

		
		
		user  = new MedicalUser(firstName,lastName,contactInfo,medicalSpecialty,medInstitution,institutionTelephone,userName,userPassword);

	}
	
	@Test
	public void shouldAssertUser2Constructor() {
		String address2 = "WcciHighStreet";
		String businessTelephone2 = "6142442444";
		
		addresses.add(address2);
		phones.add(businessTelephone2);
		contactInfo = new ContactInfo(addresses, phones, null);
			
		firstName = "Simon";
		lastName = "Bob";
		medicalSpecialty = "Therapist";
		medInstitution = "Grant";
		institutionTelephone = "800999999";
		userName = "wcciAdmin";
		userPassword = "1222";

		user2  = new MedicalUser(firstName,lastName,contactInfo,medicalSpecialty,medInstitution,institutionTelephone,userName,userPassword);

		
		assertThat(((MedicalUser) user2).getFirstName(),is(firstName));
		assertThat(((MedicalUser) user2).getLastName(),is(lastName));
		assertThat(((MedicalUser) user2).getContactInfo().getPhones(),containsInAnyOrder(businessTelephone, businessTelephone2));
		assertThat(((MedicalUser) user2).getContactInfo().getAddresses(),containsInAnyOrder(address, address2));
		assertThat(((MedicalUser) user2).getMedicalSpecialty(),is(medicalSpecialty));
		assertThat(((MedicalUser) user2).getMedicalInstitution(),is(medInstitution));
		assertThat(((MedicalUser) user2).getInstitutionTelephone(),is(institutionTelephone));
		assertThat(((MedicalUser) user2).getUserName(),is(userName));
		assertThat(((MedicalUser) user2).getPassword(),is(userPassword));
	}
	
	@Test
	public void shouldCreateMedicalUser() {
		
		user = new MedicalUser("","",null,"","","","","");		
		assertThat(user, instanceOf(MedicalUser.class));
		
	}
	
	@Test
	public void shouldRetunrMedicalUserNameMichael() {
		String actualName = ((MedicalUser) user).getFirstName();
		assertThat(actualName, is(firstName));
	}
	
	@Test
	public void shouldRetunrMedicalUserWCCI_CleveLand() {
		Collection<String> actualAddress = ((MedicalUser) user).getContactInfo().getAddresses();
		assertThat(actualAddress, contains(address));
	}
	

	@Test
	public void shouldRetunrMedicalUserBusiness7402442444() {
		Collection<String> actualContact = ((MedicalUser) user).getContactInfo().getPhones();
		assertThat(actualContact, contains(businessTelephone));
	}
	
	@Test
	public void shouldRetunrMedicalUserMtCarmel() {
		String actualMedInstitution = ((MedicalUser) user).getMedicalInstitution();	
		assertThat(actualMedInstitution, is(medInstitution));
	}
	
	@Test
	public void shouldRetunrMedicalUser8009999990() {
		String actualTelephone = ((MedicalUser) user).getInstitutionTelephone();
		assertThat(actualTelephone, is(institutionTelephone));
		
	}
		
	@Test
	public void shouldRetunrMedicalUserWcciAdmin2() {
		String actualUserName = ((MedicalUser) user).getUserName();
		assertThat(actualUserName, is(userName));
		
	}
	
	@Test
	public void shouldRetunrMedicalUser1999() {
		String actualPassword = ((MedicalUser) user).getPassword();
		assertThat(actualPassword, is(userPassword));
		
	}
	
}
