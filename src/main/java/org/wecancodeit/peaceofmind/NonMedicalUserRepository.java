package org.wecancodeit.peaceofmind;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface NonMedicalUserRepository extends CrudRepository<NonMedicalUser, Long> {
  Optional<NonMedicalUser> findByUserName(String username);
}
