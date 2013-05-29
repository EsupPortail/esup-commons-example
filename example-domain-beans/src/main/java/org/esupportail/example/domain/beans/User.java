package org.esupportail.example.domain.beans;

import java.io.Serializable;
import java.util.ArrayList;
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
	private List<Task> ownedTasks = new ArrayList<>();
	
	@OneToMany(mappedBy="assignee", orphanRemoval=true)
	private List<Task> assignedTasks= new ArrayList<>();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

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
	 * @return the ownedTasks
	 */
	public List<Task> getOwnedTasks() {
		return ownedTasks;
	}

	/**
	 * @param ownedTasks the ownedTasks to set
	 */
	public void setOwnedTasks(final List<Task> ownedTasks) {
		this.ownedTasks = ownedTasks;
	}

	/**
	 * @param ownedTasks the ownedTasks to set
	 */
	public User withOwnedTasks(final List<Task> ownedTasks) {
		this.ownedTasks = ownedTasks;
		return this;
	}

	/**
	 * @return the assignedTasks
	 */
	public List<Task> getAssignedTasks() {
		return assignedTasks;
	}

	/**
	 * @param assignedTasks the assignedTasks to set
	 */
	public void setAssignedTasks(final List<Task> assignedTasks) {
		this.assignedTasks = assignedTasks;
	}

	/**
	 * @param assignedTasks the assignedTasks to set
	 */
	public User withAssignedTasks(final List<Task> assignedTasks) {
		this.assignedTasks = assignedTasks;
		return this;
	}
	
}
