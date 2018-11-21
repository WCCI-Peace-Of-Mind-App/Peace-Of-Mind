package org.wecancodeit.peaceofmind;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class PocUserDetails implements UserDetails {
  private INonPatientUser theUser;

  public PocUserDetails(INonPatientUser user)
  {
    this.theUser = user;
  }
  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities()
  {
    Collection<GrantedAuthority> roles = new ArrayList();
    roles.add(new SimpleGrantedAuthority(this.theUser.getRole()));
    return roles;
  }

  @Override
  public String getPassword()
  {
    return this.theUser.getPassword();
  }

  @Override
  public String getUsername()
  {
    return this.theUser.getUserName();
  }

  @Override
  public boolean isAccountNonExpired()
  {
    return true;
  }

  @Override
  public boolean isAccountNonLocked()
  {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired()
  {
    return true;
  }

  @Override
  public boolean isEnabled()
  {
    return true;
  }
}