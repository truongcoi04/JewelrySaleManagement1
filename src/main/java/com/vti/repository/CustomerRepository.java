package com.vti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vti.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {

	@Query(value = "SELECT c.customer_id FROM customers c JOIN `accounts` a ON c.account_id = a.account_id WHERE a.username = :username", nativeQuery = true)
	public Integer findCustomerIdByUsername(@Param("username") String username);
	
	@Query(value = "SELECT c.customer_id FROM customers c WHERE c.account_id = :accountId", nativeQuery = true)
	public Integer findCustomerIdByAccountId(@Param("accountId") int accountId);
	

}
