/**
 * 
 */
package org.esupportail.example.domain.services;

import javax.inject.Inject;

import org.esupportail.example.dao.repositories.TaskRepository;
import org.esupportail.example.domain.beans.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author llevague
 *
 */
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class TaskServiceImpl implements TaskService {

	/**
	 * Serialization id.
	 */
	private static final long serialVersionUID = 463635900989020077L;
	
	@Inject
	private TaskRepository repository;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public Task add(final Task task) {
		return repository.save(task);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public Task update(final Task task) {
		return repository.save(task);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void delete(final Task task) {
		repository.delete(task);
	}
	
	@Override
	public Task getTask(final Long id) {
		return repository.findOne(id);
	}
	
	@Override
	public Task getTask(final String title) {
		return repository.findByTitle(title);
	}
	
	@Override
	public Page<Task> getPublicTasks(final int page, final int size, final Sort sort) {
		return repository.findByPublicTaskTrue(new PageRequest(page, size, sort));
	}

	@Override
	public Page<Task> getAllTasks(final int page, final int size, final Sort sort) {
		return repository.findAll(new PageRequest(page, size, sort));
	}
}
