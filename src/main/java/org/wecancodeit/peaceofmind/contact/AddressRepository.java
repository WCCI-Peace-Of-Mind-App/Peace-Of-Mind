package org.wecancodeit.peaceofmind.contact;

import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {

	Address findByContactInfo(ContactInfo contactInfo);

}
