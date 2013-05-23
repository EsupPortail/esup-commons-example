/**
 * 
 */
package org.esupportail.example.dao.repositories;

import org.esupportail.example.domain.beans.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

	/**
	 * Finds a {@link Task} by its title.
	 * @param title
	 * @return
	 */
	Task findByTitle(String title);
	
	/**
	 * Finds public {@link Task}s. 
	 * @return
	 */
	Page<Task> findByPublicTaskTrue(Pageable pageable);
}
