package com.vti.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.vti.dto.filter.ProductFilterForm;
import com.vti.entity.Product;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class ProductSpecification {

	@SuppressWarnings("deprecation")
	public static Specification<Product> buildWhere(String search, ProductFilterForm filterForm) {

		Specification<Product> where = null;
//		a) Search theo department name & username
		if (!StringUtils.isEmpty(search)) {
			search = search.trim();
			CustomSpecification productName = new CustomSpecification("productName", search);
			CustomSpecification mainMaterial = new CustomSpecification("mainMaterial", search);
			where = Specification.where(productName).or(mainMaterial);
		}

		// Material
		if (filterForm != null && filterForm.getMaterial() != null) {
			CustomSpecification material = new CustomSpecification("material", filterForm.getMaterial());
			if (where == null) {
				where = material;
			} else {
				where = where.and(material);
			}
		}

		return where;
	}
}

@SuppressWarnings("serial")
@RequiredArgsConstructor
class CustomSpecification implements Specification<Product> {

	@NonNull
	private String field;

	@NonNull
	private Object value;

	public Predicate toPredicate(Root<Product> product, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		if (field.equalsIgnoreCase("productName")) {
			return criteriaBuilder.like(product.get("productName"), "%" + value.toString() + "%");
		}

		if (field.equalsIgnoreCase("mainMaterial")) {
			return criteriaBuilder.like(product.get("mainMaterial"), "%" + value.toString() + "%");
		}
		
		if (field.equalsIgnoreCase("material")) {
			return criteriaBuilder.like(product.get("mainMaterial"), "%" + value.toString() + "%");
		}

		return null;
	}
}