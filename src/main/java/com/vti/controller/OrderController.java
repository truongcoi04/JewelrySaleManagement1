package com.vti.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.OrderHistoryDTO;
import com.vti.dto.filter.OrderFilterForm;
import com.vti.entity.Order;
import com.vti.service.IOrderService;

@RestController
@RequestMapping(value = "api/v1/orders")
public class OrderController {
	
	@Autowired
	private IOrderService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@GetMapping(value = "/{username}")
	public ResponseEntity<?> getListOrders(@PathVariable(name = "username") String username,
			@PageableDefault(sort = { "createdDate" }, direction = Sort.Direction.DESC) Pageable pageable,
			@RequestParam(value = "search", required = false) String search,
			OrderFilterForm filterForm){
		
		Page<Order> orderPage = service.getListOrders(username, pageable, search, filterForm);
		
		List<OrderHistoryDTO> dtos = modelMapper.map(orderPage.getContent(), new TypeToken<List<OrderHistoryDTO>>() {}.getType());
		
		Page<OrderHistoryDTO> dtoPage = new PageImpl<>(dtos, pageable, orderPage.getTotalElements());
		
		return new ResponseEntity<>(dtoPage, HttpStatus.OK);
	}
}
