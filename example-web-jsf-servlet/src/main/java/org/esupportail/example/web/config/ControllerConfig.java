package org.esupportail.example.web.config;

import org.esupportail.example.web.controllers.TaskController;
import org.esupportail.example.web.controllers.UserController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
@Lazy
public class ControllerConfig {

	@Bean
	@Scope("view")
	public TaskController taskController() {
		return new TaskController();
	}

	@Bean
	@Scope("view")
	public UserController userController() {
		return new UserController();
	}
}
