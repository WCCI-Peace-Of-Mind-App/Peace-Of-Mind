package org.wecancodeit.peaceofmind;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertNull;
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
public class ContactInfoRepositoryTest {
	
	@Resource
	IContactInfoRepository contactInfoRepo;
	
	@Resource
	private TestEntityManager entityManager;	
	
	ContactInfo contact1;
	Collection<Address> addresses = new ArrayList<>();
	Collection<Phone> phones = new ArrayList<>();
	Collection<String> emails = new ArrayList<>();

	Address address;
	Phone phone;
	String email;
	
	@Before
	public void setUp() {
		address = new Address();
		phone = new Phone();
		email = "ABC@XYZ.com";

		addresses.add(address);
		phones.add(phone);
		emails.add(email);
		contact1 = new ContactInfo(addresses, phones, null);
	}
	
	@Test
	public void shouldBeAnInstanceofContactInfo() {
		contact1 = contactInfoRepo.save(contact1);
		assertThat(contact1, instanceOf(ContactInfo.class));
	}
	
	@Test
	public void shouldVerifyContact1HasIdGreaterThan1L() {
		
	    contact1 = contactInfoRepo.save(contact1);
		long contactId = contact1.getId();

		entityManager.flush();
		entityManager.clear();
		
		Optional<ContactInfo> result = contactInfoRepo.findById(contactId);
		contact1 = result.get();
		
		assertThat(contactId, is(greaterThan(0L)));
		
	}

	@Test
	public void shouldValidateContactHasAHomeAddress() {
		
		contact1 = contactInfoRepo.save(contact1);
		long contactId = contact1.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<ContactInfo> result = contactInfoRepo.findById(contactId);
		ContactInfo contact2 = result.get();
	
		assertThat(contact2.getAddresses(),contains(address));
		

	}
	
	@Test
	public void shouldValidateContactHasAHomePhone() {
		
		contact1 = contactInfoRepo.save(contact1);
		long contactId = contact1.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<ContactInfo> result = contactInfoRepo.findById(contactId);
		ContactInfo contact2 = result.get();
	
		assertThat(contact2.getPhones(),contains(phone));
		

	}
	
	@Test
	public void shouldValidateContactHasNoEmail() {
		
		contact1 = contactInfoRepo.save(contact1);
		long contactId = contact1.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<ContactInfo> result = contactInfoRepo.findById(contactId);
		ContactInfo contact2 = result.get();
	
		//assertNull(email);
		

	}
}
