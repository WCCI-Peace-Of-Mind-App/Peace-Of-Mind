package org.wecancodeit.peaceofmind;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import javax.annotation.Resource;

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
	
	
	@Test
	public void shouldGenerateIdForEmail() {
		Email email = emailRepo.save(new Email("boop@boop.com", "personal"));
		long emailId = email.getId();
	
		
		assertThat(emailId, is(greaterThan(0L)));
		
	}
	
	@Test
	public void shouldSaveAndLoadEmail() {
		Email email = emailRepo.save(new Email("boop@boop.com", "personal"));
		long emailId = email.getId();
		
		entity.flush();
		entity.clear();
		
		Optional<Email> retreivedEmail = emailRepo.findById(emailId);
		Email result = retreivedEmail.get();
		String emailAddress = result.getEmailAddress();
		assertThat(emailAddress, is("boop@boop.com"));
		
		
		
	}

}
