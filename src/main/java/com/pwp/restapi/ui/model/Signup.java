package com.pwp.restapi.ui.model;

import org.hibernate.validator.constraints.NotEmpty;

public class Signup {
	
	@NotEmpty(message = "Username must not be empty.")
	private String username;
	
	@NotEmpty(message = "Password must not be empty.")
	private String password;
	
	@NotEmpty(message = "Confirm Password field must not be empty.")
	private String confirmPassword;
	
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
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
}