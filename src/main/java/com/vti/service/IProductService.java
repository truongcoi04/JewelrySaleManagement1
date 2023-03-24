
package com.vti.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.dto.filter.ProductFilterForm;
import com.vti.entity.Product;

public interface IProductService {

	Page<Product> getAllProducts(Pageable pageable, String search, ProductFilterForm filterForm);

	Product getProductById(int id);
}
