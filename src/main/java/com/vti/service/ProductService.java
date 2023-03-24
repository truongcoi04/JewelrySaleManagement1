package com.vti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vti.dto.filter.ProductFilterForm;
import com.vti.entity.Product;
import com.vti.repository.ProductRepository;
import com.vti.specification.ProductSpecification;


@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Page<Product> getAllProducts(Pageable pageable, String search, ProductFilterForm filterForm) {
		
		Specification<Product> where = ProductSpecification.buildWhere(search, filterForm);
		
		return productRepository.findAll(where, pageable);
	}

	@Override
	public Product getProductById(int id) {
		
		return productRepository.findById(id).get();
	}

}
