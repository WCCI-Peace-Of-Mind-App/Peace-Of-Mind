package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class MedicalUserRepositoryTest {

  @Resource
  public TestEntityManager entityManager;
  @Resource
  public MedicalUserRepository medUserRepo;
  
  @Resource
  private AddressRepository addressRepo;
  @Resource
  private PhoneRepository phoneRepo;
  @Resource
  private ContactInfoRepository contactInfoRepo;

  @Test
  public void assertSaveLoadMedicalUser()
  {
    String userNameGiven = "Cool User";
    String passwordGiven = "Lame Word";
	MedicalUser expectedMedicalUser = medUserRepo.save( new MedicalUser("first-name", "lastName",
			new ContactInfo(),
			"medicalSpecialty", "medicalInstitution", "institutionTelephone", userNameGiven, passwordGiven, null
	  )
	);
	assertTrue(expectedMedicalUser instanceof MedicalUser);
    PasswordEncoder passwordTester = new BCryptPasswordEncoder();
    assertThat(userNameGiven, is(expectedMedicalUser.getUserName()));
    assertTrue(passwordTester.matches(passwordGiven, expectedMedicalUser.getPassword()));
  }
  
  @Test
  public void assertAddressAndPhoneReturnedSaved()
  {
    String userNameGiven = "userName";
    String passwordGiven = "password";
	 Address anAddress = addressRepo.save(new Address("123 Main St", "2nd fl", "Anywhere", "XX", "00000", "Business"));
	 Phone aPhone = phoneRepo.save(new Phone("614-555-1212", "Business"));
	 ContactInfo contactInfo = contactInfoRepo.save(new ContactInfo());
	 contactInfo.addAddress(anAddress);
	 contactInfo.addPhone(aPhone);
	 
	 MedicalUser expectedMedicalUser = medUserRepo.save( new MedicalUser("first-name", "lastName",
				contactInfo,
				"medicalSpecialty", "medicalInstitution", "institutionTelephone", userNameGiven, passwordGiven, null
	   )
	 );
	 assertThat(expectedMedicalUser.getContactInfo().getAddress(), is(anAddress));
	 assertThat(expectedMedicalUser.getContactInfo().getPhone(), is(aPhone));
	   PasswordEncoder passwordTester = new BCryptPasswordEncoder();
	   assertThat(userNameGiven, is(expectedMedicalUser.getUserName()));
	   assertTrue(passwordTester.matches(passwordGiven, expectedMedicalUser.getPassword()));
  }
}