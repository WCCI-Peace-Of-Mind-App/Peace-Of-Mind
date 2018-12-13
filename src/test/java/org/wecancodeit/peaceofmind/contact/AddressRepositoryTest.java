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
public class AddressRepositoryTest {
	
	@Resource
	private TestEntityManager entity;
	
	@Resource
	private AddressRepository addressRepo;
	
	@Resource
	private ContactInfoRepository contactInfoRepo;
	
	@Resource
	private PhoneRepository phoneRepo;
	
	@Resource
	private EmailRepository emailRepo;
	
	Address address;
	Phone phone;
	Email email;
	
	ContactInfo contactInfo;
	
	long addressId;
	
	@Before
	public void setUp() {
		
		phone = phoneRepo.save(new Phone("", ""));
		email = emailRepo.save(new Email("", ""));
		address = addressRepo.save(new Address("", "", "", "", "", ""));
		addressId = address.getId();
		
		contactInfo = contactInfoRepo.save(new ContactInfo(address, email, phone));
		
		entity.flush();
		entity.clear();
	}
	
	@Test
	public void shouldHaveAnIdGreaterThan0() {
		assertThat(addressId, greaterThan(0L));
	}
	
	@Test
	public void shouldSaveAndLoadAddress() {
		Optional<Address> underTest = addressRepo.findById(addressId);
		Address testAddress = underTest.get();
		assertThat(testAddress, is(address));
	}
	
	@Test
	public void shouldEstablishRelationshipFromAddressToContactInfo() {
		Address addressInRepo = addressRepo.findByContactInfo(contactInfo);
		assertThat(addressInRepo, is(address));
	}

}
