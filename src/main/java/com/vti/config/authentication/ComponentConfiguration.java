package com.vti.config.authentication;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class ComponentConfiguration {

	@Bean
	public ModelMapper initModelMapper() {
		return new ModelMapper();
	}


	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		DefaultUriBuilderFactory defaultUriBuilderFactory = new DefaultUriBuilderFactory("https://6302de819eb72a839d74d920.mockapi.io");
		restTemplate.setUriTemplateHandler(defaultUriBuilderFactory);
		return restTemplate;
	}

}

