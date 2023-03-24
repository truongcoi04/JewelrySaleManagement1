package com.vti.controller;

import com.vti.dto.ComparativeReportDTO;
import com.vti.dto.ProductDTO;
import com.vti.dto.filter.ProductFilterForm;
import com.vti.entity.Product;
import com.vti.service.IProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "demo")
public class DemoController {

	private RestTemplate restTemplate;

	public DemoController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}



	public ComparativeReportDTO getComparativeReport() {
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2MDk4MzU5NzMsInVzZXJOYW1lIjoic21zRWR1IiwiZXhwIjoxNzM1NjY0NDAwfQ.LU1VslB2OuiepPubv2ckf7H-npfcWWG-hvVg_8Mq6Da7wkAcl7uW9WL592QXqSdCtKtzP4YYFjG-pb4GSM-_wA");

		HttpEntity<ComparativeReportDTO> entity = new HttpEntity<>(headers);

		return restTemplate.exchange("http://localhost:8080/products", HttpMethod.GET, entity, ComparativeReportDTO.class).getBody();
	}


}
