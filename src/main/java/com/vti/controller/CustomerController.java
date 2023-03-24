package com.vti.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.CustomerDTO;
import com.vti.entity.Customer;
import com.vti.service.ICustomerService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/customers")
@Validated

public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	private ModelMapper modelMapper = new ModelMapper();
	
	// get customer by username
	@GetMapping(value = "/{username}")
	public CustomerDTO findCustomerByUsername(@PathVariable(name = "username") String username) {

		// find customer by username
		Customer customer = customerService.findCustomerByUsername(username);
		
		CustomerDTO accountDTO = modelMapper.map(customer, CustomerDTO.class);

		return accountDTO;
	}
	


}
