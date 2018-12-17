package org.wecancodeit.peaceofmind.users;

import org.springframework.data.repository.CrudRepository;

public interface NonMedicalUserRepository extends CrudRepository<NonMedicalUser, Long> {

	NonMedicalUser findByUserNameAndPassword(String userName, String password);

}
