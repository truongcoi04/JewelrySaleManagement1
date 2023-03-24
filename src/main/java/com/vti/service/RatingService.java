package com.vti.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.dto.filter.CreatingRatingForm;
import com.vti.entity.Rating;
import com.vti.repository.RatingRepository;


@Service
public class RatingService implements IRatingService {

	@Autowired
	private RatingRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void createRating(CreatingRatingForm ratingForm) {
		
		TypeMap<CreatingRatingForm, Rating> typeMap = modelMapper.getTypeMap(CreatingRatingForm.class, Rating.class);
		if(typeMap == null) {
			modelMapper.addMappings(new PropertyMap<CreatingRatingForm, Rating>() {

				@Override
				protected void configure() {
					skip(destination.getRatingId());
				}
			});
		}
		
		Rating rating = modelMapper.map(ratingForm, Rating.class);
		
		repository.save(rating);
	}

	

}
