package org.wecancodeit.peaceofmind;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface MedicalUserRepository extends CrudRepository<MedicalUser, Long> {
  Optional<MedicalUser> findByUserName(String username);
}