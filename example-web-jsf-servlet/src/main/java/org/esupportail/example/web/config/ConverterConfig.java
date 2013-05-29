package org.esupportail.example.web.config;

import org.esupportail.example.web.converters.UserConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Lazy
public class ConverterConfig {
	
	@Bean
	public UserConverter userConverter() {
		return new UserConverter();
	}
}
