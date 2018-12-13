package org.wecancodeit.peaceofmind.users;

import org.wecancodeit.peaceofmind.contact.ContactInfo;

public interface IPerson {

  public String getFirstName();
  public String getLastName();
  public ContactInfo getContactInfo();
}