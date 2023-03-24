package com.vti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.vti.entity.RegistrationAccountToken;


public interface RegistrationAccountTokenRepository extends JpaRepository<RegistrationAccountToken, Integer> {

	public RegistrationAccountToken findByToken(String token);

	public boolean existsByToken(String token);
	
	@Query("	SELECT 	token	"
			+ "	FROM 	RegistrationAccountToken "
			+ " WHERE 	account.accountId = :accountId")
	public String findByAccountId(@Param("accountId") int accountId);

	@Transactional
	@Modifying
	@Query("	DELETE 							"
			+ "	FROM 	RegistrationAccountToken 	"
			+ " WHERE 	account.accountId = :accountId")
	public void deleteByAccountId(@Param("accountId") int accountId);

}
