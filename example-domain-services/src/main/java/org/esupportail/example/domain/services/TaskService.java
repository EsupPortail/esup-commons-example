package org.esupportail.example.domain.services;

import org.esupportail.example.domain.beans.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface TaskService extends DomainService<Task> {
	
	/**
	 * Retrieves a {@link Task} by its id.
	 * @param id
	 * @return
	 */
	Task getTask(Long id);
	
	/**
	 * Retrieves a {@link Task} by its title.
	 * @param title
	 * @return
	 */
	Task getTask(String title);
	
	/**
	 * Retrieves public tasks by page.
	 * @param page
	 * @param size
	 * @param sort
	 * @return
	 */
	Page<Task> getPublicTasks(int page, int size, Sort sort);

	/**
	 * Retrieves all tasks.
	 * @param page
	 * @param size
	 * @param sort
	 * @return
	 */
	Page<Task> getAllTasks(int page, int size, Sort sort);
}
