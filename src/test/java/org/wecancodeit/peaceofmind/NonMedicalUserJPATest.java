package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
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
public class NonMedicalUserJPATest {
	
	@Resource
	private TestEntityManager entityManager;
	
	@Resource
	private NonMedicalUserRepository nonMedUserRepo;
	
	@Resource
	private PhoneRepository phoneRepo;
	
	@Resource
	private AddressRepository addressRepo;
	
	@Resource
	private ContactInfoRepository contactRepo;
	
	NonMedicalUser underTest;
	long nonMedUserId;
	ContactInfo contact;
	
	@Before
	public void setUp() {
		Phone phone = new Phone ("phone", "type");
		phoneRepo.save(phone);
		Address address = new Address("street", "blarp", "city", "state", "zip", "type" );
		addressRepo.save(address);
		
		
		Collection<Phone> phones = new ArrayList<>();
		Collection<Address>addresses = new ArrayList<>();
		
		
		phones.add(phone);
		addresses.add(address);
		
		contact = new ContactInfo(addresses, phones, null);
		contactRepo.save(contact);
		underTest = nonMedUserRepo.save(new NonMedicalUser("first", "last", contact, "username", "password", "relation"));
		nonMedUserId = underTest.getId();
		
		entityManager.flush();
		entityManager.clear();
		
	}
	
	@Test
	public void shouldGenerateNonMedicalUserId() {
		assertThat(nonMedUserId, is(greaterThan(0l)));
	}
	
	@Test
	public void shouldSaveAndLoadNonMedUser() {	
		Optional<NonMedicalUser> getresult = nonMedUserRepo.findById(nonMedUserId);
		NonMedicalUser result = getresult.get();
		assertThat(result, is(underTest));
	}
	
	@Test
	public void shouldEstablishRelationshipBetweenNonMedUserAndContactInfo() {
		Optional<NonMedicalUser> getresult = nonMedUserRepo.findById(nonMedUserId);
		NonMedicalUser result = getresult.get();
		assertThat(result.getContactInfo(), is(contact));
	}

}
