package com.vti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.filter.CreatingRatingForm;
import com.vti.service.IRatingService;

@RestController
@RequestMapping(value = "api/v1/ratings")
public class RatingController {
	
	@Autowired
	private IRatingService service;
	
	
	@PostMapping()
	public ResponseEntity<?> createAccount(@RequestBody CreatingRatingForm ratingForm) {
		
		service.createRating(ratingForm);
		
		return new ResponseEntity<String>("Create successfully!", HttpStatus.OK);
	}



}
