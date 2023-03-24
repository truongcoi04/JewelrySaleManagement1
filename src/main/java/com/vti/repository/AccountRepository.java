package com.vti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vti.entity.Account;
import com.vti.entity.AccountStatus;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer>, JpaSpecificationExecutor<Account> {

	public boolean existsByUsername(String username);

	public boolean existsByEmail(String email);
	
	@Query("	SELECT 	status 		"
			+ "	FROM 	Account 		"
			+ " WHERE 	email = :email")
	public AccountStatus findStatusByEmail(@Param("email") String email);

	public Account findByUsername(String name);
	
	public Account findByEmail(String email);

	public List<Account> findAllByUsername(String username);

//	public Account findById(int id);
}
