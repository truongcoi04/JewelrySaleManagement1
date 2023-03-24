package com.vti.service;

import com.vti.dto.AccountDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vti.dto.ChangePublicProfileDTO;
import com.vti.dto.EmailFormDTO;
import com.vti.dto.UpdateAccountForm;
import com.vti.entity.Account;

import java.util.List;

public interface IAccountService extends UserDetailsService {

	Account createAccount(Account account);

	Account findAccountByEmail(String email);
	
	Account getAccountById(int id);
	
	AccountDTO findById(int id);

	Account findAccountByUsername(String username);

	void activeAccount(String token);

	void sendConfirmUserRegistrationViaEmail(String email);

	boolean existsAccountByEmail(String email);

	boolean existsAccountByUsername(String username);

	void resetPasswordViaEmail(String email);

	void resetPassword(String token, String newPassword);

	void sendResetPasswordViaEmail(String email);

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
	void changeAccountProfile(String username, ChangePublicProfileDTO dto);
	
	void updateAccount(String username, UpdateAccountForm updateAccountForm);
	
	void sendEmailToAdmin(EmailFormDTO dto);


	List<AccountDTO> findAllByUsername(String email);

}
