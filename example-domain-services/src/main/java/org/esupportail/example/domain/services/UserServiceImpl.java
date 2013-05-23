package org.esupportail.example.domain.services;

import javax.inject.Inject;

import org.esupportail.example.dao.repositories.UserRepository;
import org.esupportail.example.domain.beans.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class UserServiceImpl implements UserService {

	/**
	 * Serialization id.
	 */
	private static final long serialVersionUID = -389503765548895071L;

	@Inject
	private UserRepository repository;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public User add(final User user) {
		return repository.save(user);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public User update(final User user) {
		return repository.save(user);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void delete(final User user) {
		repository.delete(user);
	}

	@Override
	public Page<User> getAdminUsers(final int page, final int size, final Sort sort) {
		return repository.findByAdminTrue(new PageRequest(page, size, sort));
	}

	@Override
	public Page<User> getAllUsers(final int page, final int size, final Sort sort) {
		return repository.findAll(new PageRequest(page, size, sort));
	}
}
