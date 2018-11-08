package org.wecancodeit.peaceofmind;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.containsInAnyOrder;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
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
	MedicalUser expectedMedicalUser = medUserRepo.save( new MedicalUser("first-name", "lastName",
			new ContactInfo(),
			"medicalSpecialty", "medicalInstitution", "institutionTelephone", "userName", "password"
	  )
	);
	assertTrue(expectedMedicalUser instanceof MedicalUser);
  }
  
  @Test
  public void assertAddressAndPhoneReturnedSaved()
  {
	 Address anAddress = addressRepo.save(new Address("123 Main St", "2nd fl", "Anywhere", "XX", "00000", "Business"));
	 Address aSecondAddress = addressRepo.save(new Address("wet", "dfghxc", "wey", "uo", "879", "Business"));
	 Phone aPhone = phoneRepo.save(new Phone("614-555-1212", "Business"));
	 Phone aSecondPhone = phoneRepo.save(new Phone("345345", "Mobile"));
	 ContactInfo contactInfo = contactInfoRepo.save(new ContactInfo());
	 contactInfo.addAddress(anAddress);
	 contactInfo.addAddress(aSecondAddress);
	 contactInfo.addPhone(aPhone);
	 contactInfo.addPhone(aSecondPhone);
	 
	 MedicalUser expectedMedicalUser = medUserRepo.save( new MedicalUser("first-name", "lastName",
				contactInfo,
				"medicalSpecialty", "medicalInstitution", "institutionTelephone", "userName", "password"
	   )
	 );
	 assertThat(expectedMedicalUser.getContactInfo().getAddresses(), containsInAnyOrder(aSecondAddress, anAddress));
	 assertThat(expectedMedicalUser.getContactInfo().getPhones(), containsInAnyOrder(aPhone, aSecondPhone));
  }
}