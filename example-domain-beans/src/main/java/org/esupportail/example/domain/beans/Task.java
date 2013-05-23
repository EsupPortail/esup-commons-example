package org.esupportail.example.domain.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Task implements Serializable {
	
	/**
	 * Serialization id.
	 */
	private static final long serialVersionUID = 4484949580252854993L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Basic
	@Column(length=45, nullable=false, unique=true)
	private String title;

	@Basic
	@Column(length=45)
	private String description;

	@Basic
	private boolean publicTask;
	
	@Basic
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@ManyToOne
	private User owner;
	

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @param id the id to set
	 * @return
	 */
	public Task withId(final Long id) {
		this.id = id;
		return this;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * @param title the title to set
	 * @return
	 */
	public Task withTitle(final String title) {
		this.title = title;
		return this;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @param description the description to set
	 * @return
	 */
	public Task withDescription(final String description) {
		this.description = description;
		return this;
	}

	/**
	 * @return the publicTask
	 */
	public boolean isPublicTask() {
		return publicTask;
	}

	/**
	 * @param publicTask the publicTask to set
	 */
	public void setPublicTask(boolean publicTask) {
		this.publicTask = publicTask;
	}

	/**
	 * @param publicTask the publicTask to set
	 * @return
	 */
	public Task withPublicTask(boolean publicTask) {
		this.publicTask = publicTask;
		return this;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * @param date the date to set
	 * @return
	 */
	public Task withDate(final Date date) {
		this.date = date;
		return this;
	}

	/**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public Task withOwner(final User owner) {
		this.owner = owner;
		return this;
	}
}
