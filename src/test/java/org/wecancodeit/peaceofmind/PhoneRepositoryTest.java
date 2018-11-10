package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;
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
	private ContactInfoRepository contactInfoRepo;
	long phoneId;
	Phone phone;
	Phone phone2;
	Phone phoneNot;
	String phoneNumber;
	String type;
	ContactInfo contactInfo;
	long contactId;
	Collection<Phone> phonesTest = new ArrayList<>();;
	
	@Before
	public void setUp() {
		contactInfo = contactInfoRepo.save(new ContactInfo());

		type = "Home";
		phoneNumber = "614-867-5309";
		
		phone = phoneRepo.save(new Phone(phoneNumber, type));
		phoneId = phone.getId();
		
		phone2 = phoneRepo.save(new Phone("123-456-7890", "Away"));
		phoneNot = phoneRepo.save(new Phone());
		
		contactInfo.addPhone(phone);
		contactInfo.addPhone(phone2);
		
		
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
		Collection<Phone> underTest = phoneRepo.findByContactInfo(contactInfo);
		assertThat(underTest, containsInAnyOrder(phone, phone2));
	}

}
