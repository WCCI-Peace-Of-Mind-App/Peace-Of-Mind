package org.wecancodeit.peaceofmind;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class PomUserDetailsService implements UserDetailsService {
  @Autowired
  private NonMedicalUserRepository nonMedRepo;
  @Autowired
  private MedicalUserRepository medUserRepo;
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
  {
    Optional<NonMedicalUser> firstUser = nonMedRepo.findByUserName(username);
    Optional<MedicalUser> secondUser = medUserRepo.findByUserName(username);
    INonPatientUser user;
    if(firstUser.isPresent()) {
      user = firstUser.get();
    } else if(secondUser.isPresent()) {
      user = secondUser.get();
    } else {
      throw new UsernameNotFoundException("The provided username, " + username + " is not valid");
    }
    return new PocUserDetails(user);
  }
}