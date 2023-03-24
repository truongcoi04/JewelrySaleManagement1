package com.vti.dto;

public class ProfileDTO {

	private String username;

	private String email;

	private String firstName;

	private String lastName;

	private String role;

	private String status;

	private String avatarUrl;

	public ProfileDTO(String username, String email, String firstName, String lastName, String role, String status,
			String avatarUrl) {
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.status = status;
		this.avatarUrl = avatarUrl;
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

	public String getAvatarUrl() {
		return avatarUrl;
	}

}
