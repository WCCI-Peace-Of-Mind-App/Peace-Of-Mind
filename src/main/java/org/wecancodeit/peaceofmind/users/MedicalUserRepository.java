package org.wecancodeit.peaceofmind.users;

import org.springframework.data.repository.CrudRepository;

public interface MedicalUserRepository extends CrudRepository<MedicalUser, Long> {

	MedicalUser findByUserNameAndPassword(String userName, String password);

}