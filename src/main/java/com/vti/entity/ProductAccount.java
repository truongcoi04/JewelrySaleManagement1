package com.vti.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "`products_account`")
@Data
@NoArgsConstructor
public class ProductAccount implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "products_account_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productsAccountId;

	@ManyToOne()
	@JoinColumn(name = "`product_id`")
	private Product product;

	@ManyToOne()
	@JoinColumn(name = "`account_id`")
	private Account account;





}
