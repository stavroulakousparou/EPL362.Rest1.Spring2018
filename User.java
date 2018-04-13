package com.javasampleapproach.restdata.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	private String id;

	private String Username;

	private String Password;

	private String FirstName;

	private String LastName;

	private String Role;

	public User() {
	}

	public User(String id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.FirstName = firstName;
	}

	public String getFirstName() {
		return this.FirstName;
	}

	public void setLastName(String lastName) {
		this.LastName = lastName;
	}

	public String getLastName() {
		return this.LastName;
	}

	public String getId() {
		return this.id;
	}

	public void setUsername(String Username) {
		this.Username = Username;
	}

	public String getUsername() {
		return this.Username;
	}

	public String getPassword() {
		return this.Password;
	}

	public void setPassword(String Password) {
		this.Password = Password;
	}

	public String getRole() {
		return this.Role;
	}

	public void setRole(String role) {
		this.Role = role;
	}
}
