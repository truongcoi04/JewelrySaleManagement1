package com.vti.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`customers`")
@Data
@NoArgsConstructor
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "customer_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;

//	@Column(name = "account_id")
//	@OneToOne(mappedBy = "customer")
//	@OneToOne
//	@JoinColumn(name = "account_id", referencedColumnName = "account_id")
//	private int  accountId;
	
//	@Column(name = "`account_id`")
//	@OneToOne(mappedBy = "accountId")
	@OneToOne
	@JoinColumn(name = "account_id")
	private Account account;

	@Column(name = "avatar", length = 20)
	private String avatar;

	@Column(name = "address", length = 100, nullable = false)
	private String address;

	@Column(name = "purchases_time")
	private int purchasesTime;

	@Column(name = "`amount_purchased`")
	private double amountPurchased;

	@Column(name = "`sale_code`", length = 10)
	private String saleCode;

	@Column(name = "`status`", nullable = false)
	private short status = 0;

	@Column(name = "`card_code`", length = 15)
	private String cardCode;

	@Column(name = "`security_code`", length = 3)
	private String securityCode;
	
	@OneToMany(mappedBy = "customer")
	private List<Order> orders;
	
	@OneToMany(mappedBy = "customer")
	private List<Rating> ratings;


	
}
