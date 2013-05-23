package org.esupportail.example.domain.services;

import org.esupportail.example.domain.beans.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface UserService extends DomainService<User> {
		
	/**
	 * Retrieves public {@link User}s by page.
	 * @param page
	 * @param size
	 * @param sort
	 * @return
	 */
	Page<User> getAdminUsers(int page, int size, Sort sort);

	/**
	 * Retrieves all {@link User}s.
	 * @param page
	 * @param size
	 * @param sort
	 * @return
	 */
	Page<User> getAllUsers(int page, int size, Sort sort);
}
