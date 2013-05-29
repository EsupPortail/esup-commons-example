package org.esupportail.example.web.config;

import java.util.HashMap;
import java.util.Map;

import org.esupportail.example.domain.config.DomainConfig;
import org.esupportail.example.web.jsf.ViewScope;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@Import({ DomainConfig.class, ControllerConfig.class, ConverterConfig.class, WebBeansConfig.class })
public class ContextConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		final Resource[] resources = new Resource[] {
				new ClassPathResource("/properties/defaults.properties")
		};
		pspc.setLocations(resources);
		pspc.setIgnoreUnresolvablePlaceholders(true);
		return pspc;
	}

	@Bean
	public static CustomScopeConfigurer customScopeConfigurer() {
		@SuppressWarnings("serial")
		Map<String, Object> scopes = new HashMap<String, Object>() {{
				put("view", new ViewScope());
		}};

		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		configurer.setScopes(scopes);

		return configurer;
	}
}
