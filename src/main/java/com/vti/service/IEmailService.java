package com.vti.service;

public interface IEmailService {

	void sendRegistrationUserConfirm(String email);

	void sendResetPassword(String email);
	
//	void sendEmailAdmin(String email, String content);

}
