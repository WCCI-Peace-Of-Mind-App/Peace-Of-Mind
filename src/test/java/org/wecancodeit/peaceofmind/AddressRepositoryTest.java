package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

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
public class AddressRepositoryTest {
	
	@Resource
	private TestEntityManager entity;
	
	@Resource
	private AddressRepository addressRepo;
	
	@Resource
	private ContactInfoRepository contactInfoRepo;
	
	Address address;
	Address address2;
	Address addressNot;
	
	ContactInfo contactInfo;
	
	long addressId;
	
	@Before
	public void setUp() {
		contactInfo = contactInfoRepo.save(new ContactInfo());

		address = addressRepo.save(new Address("", "", "", "", "", ""));
		addressId = address.getId();
		
		address2 = addressRepo.save(new Address("", "", "", "", "", ""));
		addressNot = addressRepo.save(new Address());
				
		contactInfo.addAddress(address);
		contactInfo.addAddress(address2);
		
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
		Collection<Address> addressesInRepo = addressRepo.findByContactInfo(contactInfo);
		assertThat(addressesInRepo, containsInAnyOrder(address, address2));
	}

}
