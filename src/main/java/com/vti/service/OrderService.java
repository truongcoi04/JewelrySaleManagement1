package com.vti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vti.dto.filter.OrderFilterForm;
import com.vti.entity.Order;
import com.vti.repository.OrderRepository;
import com.vti.specification.order.OrderSpecification;


@Service
public class OrderService implements IOrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Page<Order> getListOrders(String username, Pageable pageable, String search, OrderFilterForm filterForm) {
		
		Specification<Order> where = OrderSpecification.buildWhere(username, search, filterForm);
		
		return orderRepository.findAll(where, pageable);
	}

}
