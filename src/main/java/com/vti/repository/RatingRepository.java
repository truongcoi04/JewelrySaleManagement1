package com.vti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vti.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer>, JpaSpecificationExecutor<Rating> {

}
