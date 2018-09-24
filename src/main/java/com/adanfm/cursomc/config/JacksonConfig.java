package com.adanfm.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.adanfm.cursomc.domain.PaymentCreditCard;
import com.adanfm.cursomc.domain.PaymentSlip;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JacksonConfig {
	
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PaymentCreditCard.class);
				objectMapper.registerSubtypes(PaymentSlip.class);
				super.configure(objectMapper);
			};
		};
		
		return builder;
	}
	
}
