package org.esupportail.example.domain.config;

import org.esupportail.example.dao.config.DaoConfig;
import org.esupportail.example.domain.services.TaskService;
import org.esupportail.example.domain.services.TaskServiceImpl;
import org.esupportail.example.domain.services.UserService;
import org.esupportail.example.domain.services.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Lazy
@EnableTransactionManagement
@Import(DaoConfig.class)
public class DomainConfig {

	@Bean
	public TaskService taskService() {
		return new TaskServiceImpl();
	}

	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}
}
