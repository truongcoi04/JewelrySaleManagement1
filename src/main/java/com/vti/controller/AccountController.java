package com.vti.controller;

import javax.validation.Valid;

import com.vti.dto.*;
import com.vti.entity.Product;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.entity.Account;
import com.vti.entity.Customer;
import com.vti.service.EmailService;
import com.vti.service.IAccountService;
import com.vti.service.ICustomerService;
import com.vti.service.IEmailService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/accounts")
@Validated

public class AccountController {

	@Autowired
	private IAccountService accountService;

	@Autowired
	private ICustomerService customerService;

	@GetMapping(value = "/email/{email}")
	public ResponseEntity<?> existsUserByEmail(@PathVariable(name = "email") String email) {
		// get entity
		boolean result = accountService.existsAccountByEmail(email);

		// return result
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/username/{username}")
	public ResponseEntity<?> existsUserByUsername(@PathVariable(name = "username") String username) {
		// get entity
		boolean result = accountService.existsAccountByUsername(username);

		// return result
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<?> createAccount(@Valid @RequestBody AccountDTO dto) {
		System.out.println(dto.toString());
		// create account

		Account account = accountService.createAccount(dto.toEntity());

		// create customer
		Customer customer = new Customer();
		customer.setAccount(account);

		// yaya
		customerService.createCustomer(customer);

		return new ResponseEntity<>("We have sent an email. Please check email to active account!", HttpStatus.OK);
	}

	@GetMapping("/activeUser")
	// validate: check exists, check not expired
	public ResponseEntity<?> activeUserViaEmail(@RequestParam String token) {
		// active account
		accountService.activeAccount(token);

		return new ResponseEntity<>("Active success!", HttpStatus.OK);
	}

	// resend confirm
	@GetMapping("/userRegistrationConfirmRequest")
	// validate: email exists, email not active
	public ResponseEntity<?> resendConfirmRegistrationViaEmail(@RequestParam String email) {

		accountService.sendConfirmUserRegistrationViaEmail(email);

		return new ResponseEntity<>("We have sent an email. Please check email to active account!", HttpStatus.OK);
	}

	// reset password confirm
	@GetMapping("/resetPasswordRequest")
	// validate: email exists, email not active
	public ResponseEntity<?> sendResetPasswordViaEmail(@RequestParam String email) {

		accountService.resetPasswordViaEmail(email);

		return new ResponseEntity<>("We have sent an email. Please check email to reset password!", HttpStatus.OK);
	}

	// resend reset password
	@GetMapping("/resendResetPassword")
	// validate: email exists, email not active
	public ResponseEntity<?> resendResetPasswordViaEmail(@RequestParam String email) {

		accountService.sendResetPasswordViaEmail(email);

		return new ResponseEntity<>("We have sent an email. Please check email to reset password!", HttpStatus.OK);
	}

	@GetMapping("/resetPassword")
	// validate: check exists, check not expired
	public ResponseEntity<?> resetPasswordViaEmail(@RequestParam String token, @RequestParam String newPassword) {

		// reset password
		accountService.resetPassword(token, newPassword);

		return new ResponseEntity<>("Reset Password success!", HttpStatus.OK);
	}

	@GetMapping("/profile")
	// validate: check exists, check not expired
	public ResponseEntity<?> getUserProfile(Authentication authentication) {

		// get username from token
		String username = authentication.getName();

		// get user info
		Account account = accountService.findAccountByUsername(username);

		// convert user entity to user dto
		ProfileDTO profileDto = new ProfileDTO(account.getUsername(), account.getEmail(), account.getFirstName(),
				account.getLastName(), account.getRole().toString(), account.getStatus().toString(),
				account.getAvatarUrl());

		return new ResponseEntity<>(profileDto, HttpStatus.OK);
	}

//	@PutMapping("/profile")
//	// validate: check exists, check not expired
//	public ResponseEntity<?> changeUserProfile(Authentication authentication, @RequestBody ChangePublicProfileDTO dto) {
//
//		// get username from token
//		String username = authentication.getName();
//
//		accountService.changeAccountProfile(username, dto);
//
//		return new ResponseEntity<>("Change Profile Successfully!", HttpStatus.OK);
//	}

	@PutMapping("/update")
	// validate: check exists, check not expired
	public ResponseEntity<?> updateAccount(Authentication authentication,
			@RequestBody UpdateAccountForm updateAccountForm) {

		// get username from token
		String username = authentication.getName();

		accountService.updateAccount(username, updateAccountForm);
		customerService.updateCustomer(username, updateAccountForm);

		return new ResponseEntity<>("Update Profile Successfully!", HttpStatus.OK);
	}

	@PostMapping("/email/send")
	// validate: check exists, check not expired
	public void sendEmailToAdmin(@RequestBody EmailFormDTO dto) {

		accountService.sendEmailToAdmin(dto);

	}


	@GetMapping(value = "/getAccount/{id}")
	public ResponseEntity<?> getAccount(@PathVariable(name = "id") int id) {

		AccountDTO accountDTO = accountService.findById(id);

		return new ResponseEntity<>(accountDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/getUsername/{username}")
	public ResponseEntity<?> getAccount(@PathVariable(name = "username") String username) {

		List<AccountDTO> accountDTOS = accountService.findAllByUsername(username);

		return new ResponseEntity<>(accountDTOS, HttpStatus.OK);
	}


}
