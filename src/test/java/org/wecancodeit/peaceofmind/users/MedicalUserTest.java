package org.wecancodeit.peaceofmind.users;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.wecancodeit.peaceofmind.contact.ContactInfo;

public class MedicalUserTest {
	
	ContactInfo contactInfo;
	
	MedicalUser user;
	String firstName;
	String lastName;
	String medicalSpecialty;
	String medInstitution;
	String institutionTelephone;
	String userName;
	String userPassword;
	
	MedicalUser user2;
	
	@Before
	public void setUp() {
		contactInfo = new ContactInfo();
			
		firstName = "Michael";
		lastName = "Bob";
		medicalSpecialty = "Therapist";
		medInstitution = "MtCarmel";
		institutionTelephone = "8009999990";
		userName = "WcciAdmin2";
		userPassword = "1999";

		user  = new MedicalUser(firstName,lastName,contactInfo,medicalSpecialty,medInstitution,institutionTelephone,userName,userPassword, null);

	}
	
	
	@Test
	public void shouldCreateMedicalUser() {
		assertThat(user, instanceOf(MedicalUser.class));
	}
	
	@Test
	public void shouldRetunrMedicalUserNameMichael() {
		String actualName = user.getFirstName();
		assertThat(actualName, is(firstName));
	}
	
	
	@Test
	public void shouldRetunrMedicalUserMtCarmel() {
		String actualMedInstitution = user.getMedicalInstitution();	
		assertThat(actualMedInstitution, is(medInstitution));
	}
	
	@Test
	public void shouldRetunrMedicalUser8009999990() {
		String actualTelephone = user.getInstitutionTelephone();
		assertThat(actualTelephone, is(institutionTelephone));
	}
		
	@Test
	public void shouldRetunrMedicalUserWcciAdmin2() {
		String actualUserName = user.getUserName();
		assertThat(actualUserName, is(userName));
	}
	
	@Test
	public void shouldRetunrMedicalUser1999() {
		String actualPassword = user.getPassword();
		assertThat(actualPassword, is(userPassword));
	}
	
	@Test
	public void shouldAssertCreationOfDifferentMedicalUser() {
		firstName = "Simon";
		lastName = "Bob";
		medicalSpecialty = "Therapist";
		medInstitution = "Grant";
		institutionTelephone = "800999999";
		userName = "wcciAdmin";
		userPassword = "1222";
		
		user2  = new MedicalUser(firstName,lastName,contactInfo,medicalSpecialty,medInstitution,institutionTelephone,userName,userPassword, null);
			
		assertThat(((MedicalUser) user2).getFirstName(),is(firstName));
		assertThat(((MedicalUser) user2).getLastName(),is(lastName));
		assertThat(((MedicalUser) user2).getMedicalSpecialty(),is(medicalSpecialty));
		assertThat(((MedicalUser) user2).getMedicalInstitution(),is(medInstitution));
		assertThat(((MedicalUser) user2).getInstitutionTelephone(),is(institutionTelephone));
		assertThat(((MedicalUser) user2).getUserName(),is(userName));
		assertThat(((MedicalUser) user2).getPassword(),is(userPassword));
	}
	
}