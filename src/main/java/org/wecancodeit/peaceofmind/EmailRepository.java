package org.wecancodeit.peaceofmind;

import org.springframework.data.repository.CrudRepository;

public interface EmailRepository extends CrudRepository<Email, Long> {

	Email findByContactInfo(ContactInfo contactInfo);


}
