package com.vti.dto.filter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatingRatingForm {

	private Integer productId;

	private Integer customerId;
	
	private short rate;
	
	private String content;
	
}
