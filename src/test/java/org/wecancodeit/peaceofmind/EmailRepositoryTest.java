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
public class EmailRepositoryTest {
	
	@Resource
	private TestEntityManager entity;
	
	@Resource
	private EmailRepository emailRepo;
	
	@Resource
	private ContactInfoRepository contactInfoRepo;
	
	long emailId;

	Email email;
	Email email2;
	Email emailNot;
	
	Collection<Email> emails = new ArrayList<>();
	
	ContactInfo contactInfo;
	
	@Before
	public void setUp() {
		email = emailRepo.save(new Email("boop@boop.com", "personal"));
		emailId = email.getId();

		email2 = emailRepo.save(new Email("a@b.com", "work"));
		emailNot = emailRepo.save(new Email("z@y.com", "play"));
		
		emails.add(email);
		emails.add(email2);
		
		contactInfo = contactInfoRepo.save(new ContactInfo(null, null, emails));
		
		entity.flush();
		entity.clear();
	}
	
	@Test
	public void shouldGenerateIdForEmail() {
		assertThat(emailId, is(greaterThan(0L)));
	}
	
	@Test
	public void shouldSaveAndLoadEmail() {
		Optional<Email> retreivedEmail = emailRepo.findById(emailId);
		Email result = retreivedEmail.get();
		String emailAddress = result.getEmailAddress();
		assertThat(emailAddress, is("boop@boop.com"));
	}
	
	@Test
	public void shouldEstablishRelationshipBetweenEmailAndContactInfo() {
		Collection<Email> emailsInRepo = emailRepo.findByContactInfo(contactInfo);
		assertThat(emailsInRepo, containsInAnyOrder(email, email2));
	}

}
