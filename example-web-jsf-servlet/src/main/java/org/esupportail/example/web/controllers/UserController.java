package org.esupportail.example.web.controllers;

import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import static org.esupportail.example.web.rewrite.NavigationRules.REDIRECT;
import static org.esupportail.example.web.rewrite.NavigationRules.USERS_LIST;
import static org.esupportail.example.web.rewrite.NavigationRules.USER_ADD;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.esupportail.example.domain.beans.User;
import org.esupportail.example.domain.services.UserService;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;

public class UserController {

	/**
	 * The {@link User} list.
	 */
	@Inject
	private LazyDataModel<User> usersLDM;
	
	/**
	 * The {@link UserService}.
	 */
	@Inject
	private UserService userService;
	
	/**
	 * The {@link User} to be added.
	 */
	private User newUser;
	
	/**
	 * The selected users.
	 */
	private User[] selectedUsers;
	
	@PostConstruct
	public void init() {
		newUser = new User();
		selectedUsers = new User[]{};
	}

	//////////////////////////// ACTIONS ///////////////////////////////////
	
	/**
	 * Add a {@link User} to the database.
	 * @return
	 */
	public String addUser() {
		userService.add(newUser);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(SEVERITY_INFO,
						"Ajout réussi",
						"L'utilisateur [" + newUser.getId() + "] a été ajouté."));
		return goList();
	}
	
	/**
	 * Add a {@link User} to the database.
	 * @return
	 */
	public String deleteUsers() {
		for (User u : selectedUsers) {
//			userService.delete(u);
		}
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(SEVERITY_INFO,
						"Suppression réussie",
						"Les utilisateurs sélectionnés ont été supprimés"));
		return goList();
	}

	/**
	 * Update a {@link User} to the database.
	 * @return
	 */
	public void onEdit(RowEditEvent event) { 
		User editedUser = (User) event.getObject();
		editedUser = userService.update(editedUser);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(SEVERITY_INFO,
						"Modification réussie",
						"L'utilisateur [" + editedUser.getId() + "] a été modifié."));
    }

	//////////////////////////// CALLBACKS /////////////////////////////////
	
	/**
	 * @return a callback to go to users' list.
	 */
	public String goList() {
		return USERS_LIST + REDIRECT;
	}

	/**
	 * @return a callback to go to add user form.
	 */
	public String goAdd() {
		return USER_ADD + REDIRECT;
	}

	//////////////////////////// GETTERS & SETTERS /////////////////////////
	
	/**
	 * @return the usersLDM
	 */
	public LazyDataModel<User> getUsersLDM() {
		return usersLDM;
	}

	/**
	 * @return the newUser
	 */
	public User getNewUser() {
		return newUser;
	}

	/**
	 * @param newUser the newUser to set
	 */
	public void setNewUser(final User newUser) {
		this.newUser = newUser;
	}

	/**
	 * @return the selectedUsers
	 */
	public User[] getSelectedUsers() {
		return selectedUsers;
	}

	/**
	 * @param selectedUsers the selectedUsers to set
	 */
	public void setSelectedUsers(final User[] selectedUsers) {
		this.selectedUsers = selectedUsers;
	}
	
}
