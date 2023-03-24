package com.vti.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Getter
@Setter
@NoArgsConstructor
public class UpdateAccountForm {

	private int accountId;
	
	private String username;
	
	private String image;
	
	private String fullName;
	
	private Date birthday;
	
	private String email;
	
	private String address;
	
	private String phoneNumber;


}