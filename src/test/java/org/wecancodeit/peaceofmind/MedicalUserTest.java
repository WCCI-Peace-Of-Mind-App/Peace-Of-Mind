package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.wecancodeit.peaceofmind.ContactInfo;
import org.wecancodeit.peaceofmind.MedicalUser;

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
	
	//String name, String address, ContactInfo contactInfo, String medicalSpecialty, String medicalInstitution, String institutionTelephone, String userName, String password	
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

		
		
		user  = new MedicalUser(firstName,lastName,contactInfo,medicalSpecialty,medInstitution,institutionTelephone,userName,userPassword);

	}
	
	@Test
	public void shouldAssertUser2Constructor() {
		contactInfo = new ContactInfo();
			
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