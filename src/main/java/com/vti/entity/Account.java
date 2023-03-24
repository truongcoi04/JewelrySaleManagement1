package com.vti.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`accounts`")
@Data
@NoArgsConstructor
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "account_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountId;
	
//	@OneToOne
//    @PrimaryKeyJoinColumn//a primary key column that is used as a foreign key to join to another table
//    private Customer customer;

	@Column(name = "`username`", length = 50, nullable = false, unique = true)
	private String username;

	@Column(name = "first_name", length = 50, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 50, nullable = false)
	private String lastName;

	@Formula("concat(first_name, ' ', last_name)")
	private String fullName;

	@Column(name = "birthday")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthday;

	@Column(name = "`email`", nullable = false, length = 50, unique = true)
	private String email;

	@Column(name = "`phone_number`", nullable = false, length = 15, unique = true)
	private String phoneNumber;

	@Column(name = "`password`", nullable = false, length = 800)
	private String password;

	@Column(name = "`role`")
	@Enumerated(EnumType.STRING)
	private Role role = Role.CUSTOMER;

	@Column(name = "created_date", nullable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "modified_date", nullable = true)
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "`status`", nullable = false)
	private AccountStatus status = AccountStatus.NOT_ACTIVE;

	@Column(name = "avatarUrl")
	private String avatarUrl;

	@OneToMany(mappedBy = "account")
	private List<ProductAccount> productAccount;


	public Account(String username, String firstName, String lastName, Date birthday, String email, String phoneNumber,
			String password) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;

	}



}
