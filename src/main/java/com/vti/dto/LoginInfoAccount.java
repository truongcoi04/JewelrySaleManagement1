package com.vti.dto;

public class LoginInfoAccount {

	private String token;

	private String username;

	private String email;

	private String firstName;

	private String lastName;

	private String role;

	private String status;

	public LoginInfoAccount(String token, String username, String email, String firstName, String lastName, String role,
			String status) {
		this.token = token;
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getRole() {
		return role;
	}

	public String getStatus() {
		return status;
	}

}
