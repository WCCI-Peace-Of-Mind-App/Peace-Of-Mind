package org.wecancodeit.peaceofmind;

import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {

	Address findByContactInfo(ContactInfo contactInfo);

}
