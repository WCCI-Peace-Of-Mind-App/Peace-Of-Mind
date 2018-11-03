package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class MedicalUserTest {
	
	
	Person user ;
	String firstName = "Simon";
	String lastName = "Bob";
	String address = "WcciHighStreet";
	String businessTelephone = "6142442444";
	String medicalSpecialty = "Therapist";
	String medInstitution = "Grant";
	String institutionTelephone = "800999999";
	String userName = "wcciAdmin";
	String userPassword = "1222";
	
	//String name, String address, String contactInfo, String medicalSpecialty, String medicalInstitution, String institutionTelephone, String userName, String password	
	Person user2  = new MedicalUser(firstName,lastName,address,businessTelephone,medicalSpecialty,medInstitution,institutionTelephone,userName,userPassword);
	
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void shouldAssertUser2Constructor() {
		assertThat(((MedicalUser) user2).getFirstName(),is(firstName));
		assertThat(((MedicalUser) user2).getLastName(),is(lastName));
		assertThat(((MedicalUser) user2).getContact(),is(businessTelephone));
		assertThat(((MedicalUser) user2).getAddress(),is(address));
		assertThat(((MedicalUser) user2).getMedicalSpecialty(),is(medicalSpecialty));
		assertThat(((MedicalUser) user2).getMedicalInstitution(),is(medInstitution));
		assertThat(((MedicalUser) user2).getInstitutionTelephone(),is(institutionTelephone));
		assertThat(((MedicalUser) user2).getUserName(),is(userName));
		assertThat(((MedicalUser) user2).getPassword(),is(userPassword));
	}
	
	/*
	@Test
	public void shouldCreateMedicalUser() {
		
		user = new MedicalUser("","","","","","","","");		
		assertThat(user, instanceOf(MedicalUser.class));
		
	}
	
	@Test
	public void shouldRetunrMedicalUserNameMichael() {
		
		String expectedName = "Michael";
		user = new MedicalUser(expectedName,"","","","","","","");
		String actualName = ((MedicalUser) user).getName();
		
		assertThat(actualName, is(expectedName));
		
	}

	
	@Test
	public void shouldRetunrMedicalUserNameSimon() {
		
		String expectedName = "Simon";
		user = new MedicalUser(expectedName,"","","","","","","");
		String actualName = ((MedicalUser) user).getName();
		
		assertThat(actualName, is(expectedName));
		
	}
	
	@Test
	public void shouldRetunrMedicalUserWCCIHighStreet() {
		
		String expectedAddress = "WCCIHighStreet";
		user = new MedicalUser("", expectedAddress,"","","","","","");
		String actualAddress = ((MedicalUser) user).getAddress();
		
		assertThat(actualAddress, is(expectedAddress));
		
	}
	
	@Test
	public void shouldRetunrMedicalUserWCCI_CleveLand() {
		
		String expectedAddress = "WCCI_CleveLand";
		user = new MedicalUser("", expectedAddress,"","","","","","");
		String actualAddress = ((MedicalUser) user).getAddress();
		
		assertThat(actualAddress, is(expectedAddress));
		
	}
	
	@Test
	public void shouldRetunrMedicalUserBusiness6142442444() {
		
		String expectedContact = "Business6142442444";
		user = new MedicalUser("","", expectedContact,"","","","","");
		String actualContact = ((MedicalUser) user).getContact();
		
		assertThat(actualContact, is(expectedContact));
		
	}

	@Test
	public void shouldRetunrMedicalUserBusiness7402442444() {
		
		String expectedContact = "Business7402442444";
		user = new MedicalUser("","", expectedContact,"","","","","");
		String actualContact = ((MedicalUser) user).getContact();
		
		assertThat(actualContact, is(expectedContact));
		
	}
	
	@Test
	public void shouldRetunrMedicalUserTherapist() {
		
		String expectedSpecialty = "Therapist";
		user = new MedicalUser("","","", expectedSpecialty,"","","","");
		String actualSpecialty = ((MedicalUser) user).getMedicalSpecialty();
		
		assertThat(actualSpecialty, is(expectedSpecialty));
		
	}
	
	@Test
	public void shouldRetunrMedicalUserGrantMedical() {
		
		String expectedMedInstitution = "GrantMedical";
		user = new MedicalUser("","","","", expectedMedInstitution,"","","");
		String actualMedInstitution = ((MedicalUser) user).getMedInstitution();
		
		assertThat(actualMedInstitution, is(expectedMedInstitution));
		
	}

	@Test
	public void shouldRetunrMedicalUserMtCarmel() {
		
		String expectedMedInstitution = "MtCarmel";
		user = new MedicalUser("","","","", expectedMedInstitution,"","","");
		String actualMedInstitution = ((MedicalUser) user).getMedInstitution();
		
		assertThat(actualMedInstitution, is(expectedMedInstitution));
		
	}
	
	@Test
	public void shouldRetunrMedicalUser8009999999() {
		
		String expectedTelephone = "8009999999";
		user = new MedicalUser("","","","","", expectedTelephone,"","");
		String actualTelephone = ((MedicalUser) user).getInstitutionTelephone();
		
		assertThat(actualTelephone, is(expectedTelephone));
		
	}
	
	@Test
	public void shouldRetunrMedicalUser8009999990() {
		
		String expectedTelephone = "8009999990";
		user = new MedicalUser("","","","","", expectedTelephone,"","");
		String actualTelephone = ((MedicalUser) user).getInstitutionTelephone();
		
		assertThat(actualTelephone, is(expectedTelephone));
		
	}
	
	@Test
	public void shouldRetunrMedicalUserWcciAdmin() {
		
		String expectedUserName = "WcciAdmin";
		user = new MedicalUser("","","","","","", expectedUserName,"");
		String actualUserName = ((MedicalUser) user).getUserName();
		
		assertThat(actualUserName, is(expectedUserName));
		
	}
	
	@Test
	public void shouldRetunrMedicalUserWcciAdmin2() {
		
		String expectedUserName = "WcciAdmin2";
		user = new MedicalUser("","","","","","", expectedUserName,"");
		String actualUserName = ((MedicalUser) user).getUserName();
		
		assertThat(actualUserName, is(expectedUserName));
		
	}
	
	@Test
	public void shouldRetunrMedicalUser9999() {
		
		String expectedPassword = "9999";
		user = new MedicalUser("","","","","","","", expectedPassword);
		String actualPassword = ((MedicalUser) user).getPassword();
		
		assertThat(actualPassword, is(expectedPassword));
		
	}
	
	@Test
	public void shouldRetunrMedicalUser1999() {
		
		String expectedPassword = "1999";
		user = new MedicalUser("","","","","","","", expectedPassword);
		String actualPassword = ((MedicalUser) user).getPassword();
		
		assertThat(actualPassword, is(expectedPassword));
		
	}*/
	
}
