package org.wecancodeit.peaceofmind.users;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.wecancodeit.peaceofmind.contact.ContactInfo;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class AppUser implements IPerson, IUser {

	@Id
	@GeneratedValue
	protected long id;
	
	protected String firstName;
	protected String lastName;
	protected String userName;
	protected String password;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	protected ContactInfo contactInfo;
	
	public long getId() {
		return id;
	}
		
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public AppUser() {};
	
	public AppUser(String firstName, String lastName, String userName, String password, ContactInfo contactInfo) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.contactInfo = contactInfo;
	}
	
}
