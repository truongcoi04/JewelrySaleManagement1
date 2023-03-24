package com.vti.service;

import java.util.List;
import java.util.UUID;

import com.vti.dto.*;
import com.vti.entity.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vti.event.OnResetPasswordViaEmailEvent;
import com.vti.event.OnSendRegistrationUserConfirmViaEmailEvent;
import com.vti.repository.AccountRepository;
import com.vti.repository.RegistrationAccountTokenRepository;
import com.vti.repository.ResetPasswordTokenRepository;

@Component
@Transactional
public class AccountService implements IAccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private RegistrationAccountTokenRepository registrationAccountTokenRepository;

	@Autowired
	private ResetPasswordTokenRepository resetPasswordTokenRepository;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Account getAccountById(int id) {
		return accountRepository.findById(id).get();
	}

	@Override
	public AccountDTO findById(int id) {
		Account account = accountRepository.findById(id).get();

		AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);

		return accountDTO;
	}

	@Override
	public Account createAccount(Account account) {

		// encode password
		account.setPassword(passwordEncoder.encode(account.getPassword()));

		// create account
		accountRepository.save(account);

		// create new user registration token
		createNewRegistrationAccountToken(account);

		// send email to confirm
		sendConfirmUserRegistrationViaEmail(account.getEmail());
		return account;
	}

	private void createNewRegistrationAccountToken(Account account) {

		// create new token for confirm Registration
		final String newToken = UUID.randomUUID().toString();
		RegistrationAccountToken token = new RegistrationAccountToken(newToken, account);

		registrationAccountTokenRepository.save(token);
	}

	@Override
	public void sendConfirmUserRegistrationViaEmail(String email) {
		eventPublisher.publishEvent(new OnSendRegistrationUserConfirmViaEmailEvent(email));
	}

	@Override
	public Account findAccountByEmail(String email) {
		return accountRepository.findByEmail(email);
	}

	@Override
	public Account findAccountByUsername(String username) {
		return accountRepository.findByUsername(username);
	}

	@Override
	public boolean existsAccountByEmail(String email) {
		return accountRepository.existsByEmail(email);
	}

	@Override
	public boolean existsAccountByUsername(String userName) {
		return accountRepository.existsByUsername(userName);
	}

	@Override
	public void activeAccount(String token) {

		// get token
		RegistrationAccountToken registrationAccountToken = registrationAccountTokenRepository.findByToken(token);

		// active user
		Account account = registrationAccountToken.getAccount();
		account.setStatus(AccountStatus.ACTIVE);
		accountRepository.save(account);

		// remove Registration User Token
		registrationAccountTokenRepository.deleteById(registrationAccountToken.getId());
	}

	@Override
	public void resetPasswordViaEmail(String email) {

		// find account by email
		Account account = findAccountByEmail(email);

		// remove token token if exists
		resetPasswordTokenRepository.deleteByAccountId(account.getAccountId());

		// create new reset password token
		createNewResetPasswordToken(account);

		// send email
		sendResetPasswordViaEmail(email);
	}

	@Override
	public void sendResetPasswordViaEmail(String email) {
		eventPublisher.publishEvent(new OnResetPasswordViaEmailEvent(email));
	}

	private void createNewResetPasswordToken(Account account) {

		// create new token for Reseting password
		final String newToken = UUID.randomUUID().toString();
		ResetPasswordToken token = new ResetPasswordToken(newToken, account);

		resetPasswordTokenRepository.save(token);
	}

	@Override
	public void resetPassword(String token, String newPassword) {
		// get token
		ResetPasswordToken resetPasswordToken = resetPasswordTokenRepository.findByToken(token);

		// change password
		Account account = resetPasswordToken.getAccount();
		account.setPassword(passwordEncoder.encode(newPassword));
		accountRepository.save(account);

		// remove Reset Password
		resetPasswordTokenRepository.deleteById(resetPasswordToken.getId());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Check user exists by username
		Account account = accountRepository.findByUsername(username);

		if (account == null) {
			throw new UsernameNotFoundException(username);
		}

		System.out.println(account.getRole());

		return new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(),
				AuthorityUtils.createAuthorityList(account.getRole().toString()));
	}

	@Override
	public void changeAccountProfile(String username, ChangePublicProfileDTO dto) {
		Account account = accountRepository.findByUsername(username);

//		account.setAvatarUrl(dto.getAvatarUrl());
		accountRepository.save(account);

		// TODO other field

	}

	@Override
	public void updateAccount(String username, UpdateAccountForm form) {

		Account account = accountRepository.findByUsername(username);
//		Account account = accountRepository.findByUsername(username);

		account.setUsername(form.getUsername());
		account.setFullName(form.getFullName());
		account.setBirthday(form.getBirthday());
		account.setEmail(form.getEmail());
		account.setPhoneNumber(form.getPhoneNumber());

		accountRepository.save(account);

	}

	@Override
	public void sendEmailToAdmin(EmailFormDTO dto) {
		// TODO Auto-generated method stub
		String mailSubject = "[Phản hồi từ khách hàng]";
		String content = "Sender: " +  dto.getEmail() + "\n" + "Content: " + dto.getContent(); 
		emailService.sendEmail("nguyenphuhung02@gmail.com", mailSubject, content);
		
	}

	@Override
	public List<AccountDTO> findAllByUsername(String username) {
		List<Account> accounts = accountRepository.findAllByUsername(username);

		List<AccountDTO> accountDTOS = modelMapper.map(accounts, new TypeToken<List<AccountDTO>>() {}.getType());

		return accountDTOS;
	}


}
