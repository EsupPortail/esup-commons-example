/**
 * ESUP-Portail Blank Application - Copyright (c) 2006 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-blank
 */
package org.esupportail.example.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.esupportail.commons.exceptions.ConfigException;
import org.esupportail.commons.exceptions.UserNotFoundException;
import org.esupportail.commons.services.application.Version;
import org.esupportail.commons.services.logging.Logger;
import org.esupportail.commons.services.logging.LoggerImpl;
import org.esupportail.commons.utils.Assert;
import org.esupportail.example.dao.DaoService;
import org.esupportail.example.domain.beans.Information;
import org.esupportail.example.domain.beans.User;
import org.esupportail.example.domain.beans.VersionManager;
import org.springframework.beans.factory.InitializingBean;

/**
 * The basic implementation of DomainService.
 * 
 * See /properties/domain/domain-example.xml
 */
public class DomainServiceImpl implements DomainService, InitializingBean {

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = -8200845058340254019L;

	/**
	 * {@link DaoService}.
	 */
	private DaoService daoService;

	/**
	 * {@link LdapUserService}.
	 */

	/**
	 * A logger.
	 */
	private final Logger logger = new LoggerImpl(getClass());

	/**
	 * Bean constructor.
	 */
	public DomainServiceImpl() {
		super();
	}

	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.debug("in afterPropertiesSet");
		Assert.notNull(this.daoService, 
				"property daoService of class " + this.getClass().getName() + " can not be null");
	}

	//////////////////////////////////////////////////////////////
	// User
	//////////////////////////////////////////////////////////////

	/**
	 * @see org.esupportail.example.domain.DomainService#getUsers()
	 */
	@Override
	public List<User> getUsers() {
		return this.daoService.getUsers();
	}


	/**
	 * @see org.esupportail.example.domain.DomainService#getUser(java.lang.String)
	 */
	@Override
	public User getUser(String id) throws UserNotFoundException {
		return this.daoService.getUser(id);
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#deleteUser(org.esupportail.example.domain.beans.User)
	 */
	@Override
	public void deleteUser(User user) {
		daoService.deleteUser(user);
	}

	@Override
	public void deleteUser(String id) {
		User user = daoService.getUser(id);
		daoService.deleteUser(user);
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#addUser(org.esupportail.example.domain.beans.User)
	 */
	@Override
	public void addUser(User user) {
		User tmp = daoService.getUser(user.getId());
		if (tmp == null) { 
			// user does not already exists in database
			Information information = new Information();
			information.setInformationKey("INSERT_DATE");
			information.setInformationValue(getDateTime());
			List<Information> informations = user.getInformations();
			if (informations == null) {
				informations = new ArrayList<Information>();
			} 
			informations.add(information);
			user.setInformations(informations);
			daoService.addUser(user);			
		}
		else {
			Information information = new Information();
			information.setInformationKey("UPDATE_DATE");
			information.setInformationValue(getDateTime());
			List<Information> informations = user.getInformations();
			if (informations == null) {
				informations = new ArrayList<Information>();
			} 
			informations.addAll(tmp.getInformations());
			informations.add(information);
			user.setInformations(informations);
			daoService.updateUser(user);
		}
		
	}

	private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

	//////////////////////////////////////////////////////////////
	// Version
	//////////////////////////////////////////////////////////////

	/**
	 * @see org.esupportail.example.domain.DomainService#getDatabaseVersion()
	 */
	@Override
	public Version getDatabaseVersion() {
		VersionManager versionManager = daoService.getVersionManager();
		if (versionManager == null) {
			//database is not initialized return null to checkVersion()
			return null;
		}
		Version version = new Version(versionManager.getVersion());
		return version;
	}

	/**
	 * @see org.esupportail.example.domain.DomainService#updateDatabaseVersion(java.lang.String)
	 */
	@Override
	public void updateDatabaseVersion(String versionNumber) {
		VersionManager versionManager =  new VersionManager();
		versionManager.setVersion(versionNumber);
		daoService.deleteVersionManager();
		daoService.addVersionManager(versionManager);
	}

	//////////////////////////////////////////////////////////////
	// Misc
	//////////////////////////////////////////////////////////////

	/**
	 * @param daoService the daoService to set
	 */
	public void setDaoService(final DaoService daoService) {
		this.daoService = daoService;
	}

}
