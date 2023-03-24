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
@Table(name = "`rating`")
@Data
@NoArgsConstructor
public class Rating implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "rating_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ratingId;

	@ManyToOne
	@JoinColumn(name = "`product_id`")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "`customer_id`")
	private Customer customer;

	@Column(name = "`rate`")
	private short rate;

	@Column(name = "`content`", length = 500)
	private String content;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate = new Date();
	
}
