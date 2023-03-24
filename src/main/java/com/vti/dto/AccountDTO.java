package com.vti.dto;

import java.util.Date;
import java.util.List;

import com.vti.entity.Account;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDTO {
	
	private int customerId;
	
//	private int accountUsername;

	private int accountId;

	private String avatar;
	
	private String address;

	private String username;

	private String phoneNumber;

	private Date birthday;

	private String fullName;

	private String email;

	private String password;

	private String firstName;

	private String lastName;

	private List<ProductDTO> product;

	public Account toEntity() {
		return new Account(username, firstName, lastName, birthday, email, phoneNumber, password);
	}

	@Data
	@NoArgsConstructor
	public static class ProductDTO {

		private String productName;
	}



}