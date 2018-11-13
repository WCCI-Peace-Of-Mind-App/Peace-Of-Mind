package org.wecancodeit.peaceofmind;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<Phone, Long> {

	Collection<Phone> findByContactInfoContains(ContactInfo contactInfo);

	Phone findByContactInfo(ContactInfo contactInfo);


}
