package org.esupportail.example.domain.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User implements Serializable {
	
	/**
	 * Serialization id.
	 */
	private static final long serialVersionUID = 4959613084567005778L;

	@Id
	@Column(length=45)
	private String id;
	
	@Basic
	@Column(length=45)
	private String displayName;
	
	@Basic
	@Column(length=45)
	private String language;
	
	@Basic
	@Column(length=80)
	private String email;
	
	@Basic
	private boolean admin;
	
	@OneToMany(mappedBy="owner", orphanRemoval=true)
	private List<Task> tasks;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(final String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(final String language) {
		this.language = language;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * @return the admin
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/**
	 * @return the tasks
	 */
	public List<Task> getTasks() {
		return tasks;
	}

	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(final List<Task> tasks) {
		this.tasks = tasks;
	}

	/**
	 * @param tasks the tasks to set
	 */
	public User withTasks(final List<Task> tasks) {
		this.tasks = tasks;
		return this;
	}
	
}
