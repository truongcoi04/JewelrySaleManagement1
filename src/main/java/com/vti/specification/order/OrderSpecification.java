package com.vti.specification.order;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.vti.dto.filter.OrderFilterForm;
import com.vti.entity.Account;
import com.vti.entity.Customer;
import com.vti.entity.Order;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class OrderSpecification {

	@SuppressWarnings("deprecation")
	public static Specification<Order> buildWhere(String username, String search, OrderFilterForm filterForm) {

		Specification<Order> where = null;

		// init
		CustomSpecification init = new CustomSpecification("init", "init");
		where = Specification.where(init);

		// username
		if (!StringUtils.isEmpty(username)) {
			username = username.trim();
			CustomSpecification usernameSpecification = new CustomSpecification("username", username);
			where = where.and(usernameSpecification);
		}
		// search
		if (!StringUtils.isEmpty(search)) {
			search = search.trim();
			CustomSpecification searchSpecification = new CustomSpecification("search", search);
			where = where.and(searchSpecification);
		}

		// Filter
		if (filterForm == null) {
			return where;
		}

		// min created date
		if (filterForm.getMinCreatedDate() != null) {
			CustomSpecification minCreatedDate = new CustomSpecification("minCreatedDate",
					filterForm.getMinCreatedDate());
			where = where.and(minCreatedDate);
		}

		// max created date
		if (filterForm.getMaxCreatedDate() != null) {
			CustomSpecification maxCreatedDate = new CustomSpecification("maxCreatedDate",
					filterForm.getMaxCreatedDate());
			where = where.and(maxCreatedDate);
		}

		return where;
	}
}

@SuppressWarnings("serial")
@RequiredArgsConstructor
class CustomSpecification implements Specification<Order> {

	@NonNull
	private String field;
	@NonNull
	private Object value;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		if (field.equalsIgnoreCase("init")) {
			return criteriaBuilder.equal(criteriaBuilder.literal(1), 1);
		}

		if (field.equalsIgnoreCase("username")) {
			Join<Order, Customer> customerJoin = root.join("customer", JoinType.INNER);
			Join<Customer, Account> acccJoin = customerJoin.join("account", JoinType.INNER);

			return criteriaBuilder.equal(acccJoin.get("username"), value.toString());
		}

		if (field.equalsIgnoreCase("search")) {
			Join join = root.join("product", JoinType.INNER);
			return criteriaBuilder.like(join.get("productName"), "%" + value.toString() + "%");
		}
		
		if (field.equalsIgnoreCase("minCreatedDate")) {
			return criteriaBuilder.greaterThanOrEqualTo(
					root.get("createdDate").as(java.sql.Date.class),
					(Date) value);
		}
	
		if (field.equalsIgnoreCase("maxCreatedDate")) {
			return criteriaBuilder.lessThanOrEqualTo(
					root.get("createdDate").as(java.sql.Date.class),
					(Date) value);
		}
		return null;
	}
}
