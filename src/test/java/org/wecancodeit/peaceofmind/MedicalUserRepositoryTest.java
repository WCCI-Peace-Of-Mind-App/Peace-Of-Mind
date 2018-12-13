package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wecancodeit.peaceofmind.contact.Address;
import org.wecancodeit.peaceofmind.contact.AddressRepository;
import org.wecancodeit.peaceofmind.contact.ContactInfo;
import org.wecancodeit.peaceofmind.contact.ContactInfoRepository;
import org.wecancodeit.peaceofmind.contact.Phone;
import org.wecancodeit.peaceofmind.contact.PhoneRepository;
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
			"medicalSpecialty", "medicalInstitution", "institutionTelephone", "userName", "password", null
	  )
	);
	assertTrue(expectedMedicalUser instanceof MedicalUser);
  }
  
  @Test
  public void assertAddressAndPhoneReturnedSaved()
  {
	 Address anAddress = addressRepo.save(new Address("123 Main St", "2nd fl", "Anywhere", "XX", "00000", "Business"));
	 Phone aPhone = phoneRepo.save(new Phone("614-555-1212", "Business"));
	 ContactInfo contactInfo = contactInfoRepo.save(new ContactInfo());
	 contactInfo.addAddress(anAddress);
	 contactInfo.addPhone(aPhone);
	 
	 MedicalUser expectedMedicalUser = medUserRepo.save( new MedicalUser("first-name", "lastName",
				contactInfo,
				"medicalSpecialty", "medicalInstitution", "institutionTelephone", "userName", "password", null
	   )
	 );
	 assertThat(expectedMedicalUser.getContactInfo().getAddress(), is(anAddress));
	 assertThat(expectedMedicalUser.getContactInfo().getPhone(), is(aPhone));
  }
}