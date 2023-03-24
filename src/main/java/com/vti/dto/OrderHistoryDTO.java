package com.vti.dto;

import java.util.Date;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.entity.Customer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderHistoryDTO {

	private int productId;
	
	private String productName;
	
	private String customerAccountUsername;
	
	private String productImage;
	
	private float price;
	
	private int quantity;
	
	private int totalCurrency;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createdDate;
	
}
