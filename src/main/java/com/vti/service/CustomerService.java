package com.vti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vti.dto.UpdateAccountForm;
import com.vti.entity.Account;
import com.vti.entity.Customer;
import com.vti.repository.CustomerRepository;

@Component
@Transactional
public class CustomerService implements ICustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private IAccountService accountService;

	@Override
	public Customer findCustomerByUsername(String username) {
		// TODO Auto-generated method stub
		// find customer id by user name

		Integer customerId = customerRepository.findCustomerIdByUsername(username);
		return customerRepository.findById(customerId).get();
	}

	@Override
	public void createCustomer(Customer customer) {
		customerRepository.save(customer);
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCustomer(String username, UpdateAccountForm form) {

		// get customer id by account id

		Integer customerId = customerRepository.findCustomerIdByAccountId(form.getAccountId());

		// find customer by customer id
		Customer customer = customerRepository.findById(customerId).get();

		customer.setAddress(form.getAddress());
		customer.setAvatar(form.getImage());

	}

}
