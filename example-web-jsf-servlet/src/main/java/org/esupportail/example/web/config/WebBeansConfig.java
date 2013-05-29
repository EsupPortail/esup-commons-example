package org.esupportail.example.web.config;

import org.esupportail.example.domain.beans.Task;
import org.esupportail.example.domain.beans.User;
import org.esupportail.example.web.beans.TaskLazyDataModel;
import org.esupportail.example.web.beans.UserLazyDataModel;
import org.primefaces.model.LazyDataModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
@Lazy
public class WebBeansConfig {
	
	@Bean
	@Scope("prototype")
	public LazyDataModel<Task> publicTasksLDM() {
		return new TaskLazyDataModel().withPublicFilter(true);
	}

	@Bean
	@Scope("prototype")
	public LazyDataModel<Task> tasksLDM() {
		return new TaskLazyDataModel();
	}

	@Bean
	@Scope("prototype")
	public LazyDataModel<User> usersLDM() {
		return new UserLazyDataModel();
	}
}
