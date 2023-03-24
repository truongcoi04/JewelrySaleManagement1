package com.vti.service;

import com.vti.dto.UpdateAccountForm;
import com.vti.entity.Customer;

public interface ICustomerService {

	Customer findCustomerByUsername(String username);
	
	void createCustomer(Customer customer);
	
	void updateCustomer(String username, UpdateAccountForm updateAccountForm);

}
