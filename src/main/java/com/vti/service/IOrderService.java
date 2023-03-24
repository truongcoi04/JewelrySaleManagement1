
package com.vti.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.dto.filter.OrderFilterForm;
import com.vti.entity.Order;

public interface IOrderService {

	Page<Order> getListOrders(String username, Pageable pageable, String search, OrderFilterForm filterForm);
	
}
