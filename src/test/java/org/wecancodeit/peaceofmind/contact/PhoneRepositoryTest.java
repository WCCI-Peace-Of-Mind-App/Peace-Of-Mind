package org.wecancodeit.peaceofmind.contact;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class PhoneRepositoryTest {

	@Resource
	private TestEntityManager entity;

	@Resource
	private PhoneRepository phoneRepo;

	@Resource
	private EmailRepository emailRepo;

	@Resource
	private AddressRepository addyRepo;

	@Resource
	private ContactInfoRepository contactInfoRepo;
	long phoneId;
	Phone phone;
	String phoneNumber;
	String type;
	ContactInfo contactInfo;
	Address address;
	Email email;
	long contactId;

	@Before
	public void setUp() {

		address = addyRepo.save(new Address("", "", "", "","", ""));
		email = emailRepo.save(new Email("", ""));

		type = "Home";
		phoneNumber = "614-867-5309";

		phone = phoneRepo.save(new Phone(phoneNumber, type));
		phoneId = phone.getId();


		contactInfo = contactInfoRepo.save(new ContactInfo(address, email, phone));

		entity.flush();
		entity.clear();
	}

	@Test
	public void shouldHaveAnId() {
		assertThat(phoneId, greaterThan(0L));
	}

	@Test
	public void shouldSaveAndLoadPhoneToRepo() {
		Optional<Phone> underTest = phoneRepo.findById(phoneId);
		Phone testPhone = underTest.get();
		assertThat(testPhone, is(phone));
	}

	@Test
	public void shouldEstablishRelationshipWithContactInfoAndPhone() {
		Phone result = phoneRepo.findByContactInfo(contactInfo);
		assertThat (result, is(phone));
	}

}
