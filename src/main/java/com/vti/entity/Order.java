package com.vti.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`orders`")
@Data
@NoArgsConstructor
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "order_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;

	@ManyToOne
	@JoinColumn(name = "`customer_id`")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "`product_id`")
	private Product product;

	@Column(name = "`quantity`", nullable = false)
	private int quantity = 0;

	@Column(name = "`total_currency`", nullable = false)
	private double totalCurrency = 0;

	@Column(name = "`payment_methods`", nullable = false)
	private short paymentMethods = 1;

	@Column(name = "`note`", length = 500)
	private String note;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "shipped_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date shippedDate;

	@Column(name = "received_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date receivedDate;

	@Column(name = "returned_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date returnedDate;

	@Column(name = "`status`", nullable = false)
	private short status = 0;

}
