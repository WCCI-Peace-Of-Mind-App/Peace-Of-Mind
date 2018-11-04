package org.wecancodeit.peaceofmind;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.containsInAnyOrder;
import java.util.ArrayList;
import java.util.List;

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
  public IMedicalUserRepository medUserRepo;
  @Test
  public void assertSaveLoadMedicalUser()
  {
	List<Address> addressPayload = new ArrayList();
	List<Phone> phonePayload = new ArrayList();
	List<String> emailPayload = null;
	addressPayload.add(new Address("123 Main St", "2nd fl", "Anywhere", "XX", "00000", "Business"));
	phonePayload.add(new Phone("614-555-1212", "Business"));
	MedicalUser expectedMedicalUser = medUserRepo.save( new MedicalUser("first-name", "lastName",
			new ContactInfo(
					addressPayload,
					phonePayload,
					emailPayload),
			"medicalSpecialty", "medicalInstitution", "institutionTelephone", "userName", "password"
	  )
	);
//	MedicalUser expectedMedicalUser = medUserRepo.save(new MedicalUser());
	assertTrue(expectedMedicalUser instanceof MedicalUser);
  }
  
  @Test
  public void assertAddressAndPhoneReturnedSaved()
  {
	 List<Address> addressPayload = new ArrayList();
	 List<Phone> phonePayload = new ArrayList();
	 List<String> emailPayload = null;
	 Address anAddress = null;
	 Address aSecondAddress = null;
	 Phone aPhone = null;
	 Phone aSecondPhone = null; 
	 addressPayload.add(anAddress = new Address("123 Main St", "2nd fl", "Anywhere", "XX", "00000", "Business"));
	 addressPayload.add(aSecondAddress = new Address("wet", "dfghxc", "wey", "uo", "879", "Business"));
	 phonePayload.add(aSecondPhone = new Phone("345345", "Mobile"));
	 phonePayload.add(aPhone = new Phone("614-555-1212", "Business"));
	 MedicalUser expectedMedicalUser = medUserRepo.save( new MedicalUser("first-name", "lastName",
				new ContactInfo(
						addressPayload,
						phonePayload,
						emailPayload),
				"medicalSpecialty", "medicalInstitution", "institutionTelephone", "userName", "password"
	   )
	 );
	 assertThat(expectedMedicalUser.getContactInfo().getAddresses(), containsInAnyOrder(aSecondAddress, anAddress));
	 assertThat(expectedMedicalUser.getContactInfo().getPhones(), containsInAnyOrder(aPhone, aSecondPhone));
  }
}