package com.vti.dto;

import java.util.Date;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {

	private int productId;
	
	private String productName;
	
	private String mainMaterial;
	
	private float price;
		
	private String image;
	
	private int quantity;
	
	private int warehouseQuantity;

	private int soldQuantity;
	
	private short status;
	
	private double rateAvg;
	
	private String content;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createdDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date saleStartDate;
	
	private List<RatingDTO> ratings;
	
	
	@Data
	@NoArgsConstructor
	public static class RatingDTO {

		private int ratingId;

		private short rate;
	}

	
}
