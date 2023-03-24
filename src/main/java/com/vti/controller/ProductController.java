package com.vti.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.ProductDTO;
import com.vti.dto.filter.ProductFilterForm;
import com.vti.entity.Product;
import com.vti.service.IProductService;

@RestController
@RequestMapping(value = "api/v1/products")
public class ProductController {
	
	@Autowired
	private IProductService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@GetMapping()
	public ResponseEntity<?> getAllProducts(
			@PageableDefault(sort = {"createdDate"}, direction = Direction.DESC) Pageable pageable, 
			@RequestParam(value = "search", required = false) String search, 
			ProductFilterForm filterForm){
		
		 Page<Product> productPage = service.getAllProducts(pageable, search, filterForm);
		
		List<ProductDTO> dtos = modelMapper.map(productPage.getContent(), new TypeToken<List<ProductDTO>>() {}.getType());
		
		Page<ProductDTO> dtoPage = new PageImpl<>(dtos, pageable, productPage.getTotalElements());
		
		return new ResponseEntity<>(dtoPage, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getProductById(@PathVariable(name = "id") int id) {
		
		Product product = service.getProductById(id);
		
		ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
		
		if(productDTO != null && productDTO.getRatings() != null) {
			if(productDTO.getRatings().size() != 0) {
				int cnt = 0;
				int rateCnt = 0;
				double avg = 0.0;
				for(ProductDTO.RatingDTO rate : productDTO.getRatings()) {
					cnt++;
					rateCnt = rateCnt + rate.getRate();
				}
				avg = (double) rateCnt / (double) cnt;
				productDTO.setRateAvg(Math.ceil(avg*10)/10);
				
			}else {
				productDTO.setRateAvg(0.0);
			}
		}
		
		return new ResponseEntity<>(productDTO, HttpStatus.OK);
	}


}
