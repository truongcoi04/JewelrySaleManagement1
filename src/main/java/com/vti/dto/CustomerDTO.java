package com.vti.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDTO {

	private int accountId;
	
	private String accountUsername;

	private String avatar;
	
	private String accountFullName;

	private Date accountBirthday;

	private String accountEmail;

	private String address;

	private String accountPhoneNumber;

}