package com.vti.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`products`")
@Data
@NoArgsConstructor
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "product_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;

	@Column(name = "`product_name`", length = 50, nullable = false, unique = true)
	private String productName;

	@Column(name = "`main_material`", length = 50, nullable = false)
	private String mainMaterial;

	@Column(name = "`price`", nullable = false)
	private double price;

	@Column(name = "`image`", length = 30)
	private String image;

	@Column(name = "`quantity`", nullable = false)
	private int quantity = 0;

	@Column(name = "`warehouse_quantity`", nullable = false)
	private int warehouseQuantity = 0;

	@Column(name = "`sold_quantity`", nullable = false)
	private int soldQuantity = 0;

	@Column(name = "`status`", nullable = false)
	private short status = 0;

	@Column(name = "`content`", length = 1000)
	private String content;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "sale_start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date saleStartDate;
	
	@OneToMany(mappedBy = "product")
	private List<Order> orders;
	
	@OneToMany(mappedBy = "product")
	private List<Rating> ratings;

	@OneToMany(mappedBy = "product")
	private List<ProductAccount> productAccount;

}
