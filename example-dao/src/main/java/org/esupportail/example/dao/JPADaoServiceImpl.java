/**
 * ESUP-Portail Blank Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-blank
 */
package org.esupportail.example.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.esupportail.commons.dao.AbstractGenericJPADaoService;
import org.esupportail.commons.services.application.Version;
import org.esupportail.example.domain.beans.Information;
import org.esupportail.example.domain.beans.User;
import org.esupportail.example.domain.beans.VersionManager;
import org.springframework.core.enums.LetterCodedLabeledEnum;

/**
 * The Hiberate implementation of the DAO service.
 */
public class JPADaoServiceImpl extends AbstractGenericJPADaoService implements DaoService {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 3152554337896617315L;
	
	/**
	 * JPA entity manager
	 */
	EntityManager entityManager;

	/**
	 * Bean constructor.
	 */
	public JPADaoServiceImpl() {
		super();
	}
	
	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		
	}

	//////////////////////////////////////////////////////////////
	// User
	//////////////////////////////////////////////////////////////

	/**
	 * @param em the em to set
	 */
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}

	/**
	 * @see org.esupportail.commons.dao.AbstractGenericJPADaoService#getEntityManager()
	 */
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	/**
	 * @see org.esupportail.example.dao.DaoService#getUser(java.lang.String)
	 */
	public User getUser(final String id) {
		User u = entityManager.find(User.class, id);
		return u;
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#getUsers()
	 */
	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		Query q = entityManager.createQuery("SELECT user FROM User user");
		List<User> ret = (List<User>)q.getResultList();
		return ret;
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#addUser(org.esupportail.example.domain.beans.User)
	 */
	public void addUser(final User user) {
		entityManager.persist(user);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#deleteUser(org.esupportail.example.domain.beans.User)
	 */
	public void deleteUser(final User user) {
		User tmp = entityManager.find(User.class, user.getId());
		entityManager.remove(tmp);
	}

	/**
	 * @see org.esupportail.example.dao.DaoService#updateUser(org.esupportail.example.domain.beans.User)
	 */
	public void updateUser(final User user) {
		entityManager.merge(user);
	}

	
	//////////////////////////////////////////////////////////////
	// version
	//////////////////////////////////////////////////////////////

	@Override
	public VersionManager getVersionManager() {
		Query q = entityManager.createQuery("SELECT versionManager FROM VersionManager versionManager");
		@SuppressWarnings("unchecked")
		List<VersionManager> list = (List<VersionManager>)q.getResultList();
		if (!list.isEmpty()) {
			return list.get(0);			
		} else {
			return null;
		}
	}

	@Override
	public void addVersionManager(VersionManager versionManager) {
		entityManager.persist(versionManager);
	}

	@Override
	public void deleteVersionManager() {
		Query q = entityManager.createQuery("DELETE FROM VersionManager");
		q.executeUpdate();
	}

}
