package com.naga.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LoginRecords {
	@Id
	private String username;
	private String password;
	private String roles;
	private boolean enabled;
	public LoginRecords(String username, String password, String roles, boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.enabled = enabled;
	}
	public LoginRecords() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
