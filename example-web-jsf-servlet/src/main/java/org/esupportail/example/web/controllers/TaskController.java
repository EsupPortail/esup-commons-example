package org.esupportail.example.web.controllers;

import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import static org.esupportail.example.web.rewrite.NavigationRules.REDIRECT;
import static org.esupportail.example.web.rewrite.NavigationRules.TASKS_LIST;
import static org.esupportail.example.web.rewrite.NavigationRules.TASK_ADD;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.esupportail.example.domain.beans.Task;
import org.esupportail.example.domain.beans.User;
import org.esupportail.example.domain.services.TaskService;
import org.esupportail.example.domain.services.UserService;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.push.PushContextFactory;
import org.springframework.data.domain.Sort;

public class TaskController {
	
	/**
	 * The {@link Task} list.
	 */
	@Resource(name="tasksLDM")
	private LazyDataModel<Task> tasksLDM;
	
	/**
	 * The {@link Task} public list.
	 */
	@Resource(name="publicTasksLDM")
	private LazyDataModel<Task> publicTasksLDM;
	
	/**
	 * The taskService.
	 */
	@Inject
	private TaskService taskService;

	/**
	 * The userService.
	 */
	@Inject
	private UserService userService;

	/**
	 * The {@link Task} to be added.
	 */
	private Task newTask;
	
	/**
	 * The selected tasks.
	 */
	private Task[] selectedTasks;
	
	
	@PostConstruct
	public void init() {
		newTask = new Task();
		selectedTasks = new Task[]{};
	}

	//////////////////////////// ACTIONS ///////////////////////////////////
	

	/**
	 * Add a {@link Task} to the database.
	 * @return
	 */
	public String addTask() {
		final FacesContext fc = FacesContext.getCurrentInstance();
		final String login = "llevague";
		final User u = userService.getUser(login);
		if (u == null) {
			fc.addMessage(
					null,
					new FacesMessage(SEVERITY_INFO,
							"Erreur durant l'ajout",
							"L'utilisateur [" + login + "] n'a pas été trouvé dans la liste des utilisateurs. Abandon"));
			return null;
		}
		newTask = taskService.add(newTask.withOwner(u));
		push();
		fc.addMessage(
				null,
				new FacesMessage(SEVERITY_INFO,
						"Ajout réussi",
						"L'utilisateur [" + newTask.getTitle() + "] a été ajouté."));
		return goList();
	}
	

	/**
	 * Add a {@link Task} to the database.
	 * @return
	 */
	public String addMockTask() {
		final String title = "Tâche bidon";
		final String desc = "Description bidon";
		final Date date = new Date();
		newTask = new Task().withTitle(title + " " + date.getTime())
				.withDescription(desc).withDate(date).withPublicTask(true);
		return addTask();
	}
	
	/**
	 * Delete selected {@link Task}s from the database.
	 * @return
	 */
	public String deleteTasks() {
		for (Task t : selectedTasks) {
			taskService.delete(t);
		}
		push();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(SEVERITY_INFO,
						"Suppression réussie",
						"Les tâches sélectionnées ont été supprimées"));
		return goList();
	}

	/**
	 * Update a {@link Task} to the database.
	 * @return
	 */
	public void onEdit(RowEditEvent event) { 
		Task editedTask = (Task) event.getObject();
		editedTask = taskService.update(editedTask);
		push();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(SEVERITY_INFO,
						"Modification réussie",
						"La tâche [" + editedTask.getTitle() + "] a été modifiée."));
    }
	
	private void push() {
		PushContextFactory.getDefault().getPushContext().push("/tasks", tasksLDM);
	}

	//////////////////////////// CALLBACKS /////////////////////////////////
	
	
	/**
	 * @return a callback to go to users' list.
	 */
	public String goList() {
		return TASKS_LIST + REDIRECT;
	}

	/**
	 * @return a callback to go to add user form.
	 */
	public String goAdd() {
		return TASK_ADD + REDIRECT;
	}

	//////////////////////////// GETTERS & SETTERS /////////////////////////

	
	public List<User> getUsers() {
		return userService.getAllUsers(new Sort("displayName"));
	}
	
	/**
	 * @return the tasksLDM
	 */
	public LazyDataModel<Task> getTasksLDM() {
		return tasksLDM;
	}

	/**
	 * @return the publicTasksLDM
	 */
	public LazyDataModel<Task> getPublicTasksLDM() {
		return publicTasksLDM;
	}

	/**
	 * @return the newTask
	 */
	public Task getNewTask() {
		return newTask;
	}

	/**
	 * @param newTask the newTask to set
	 */
	public void setNewTask(final Task newTask) {
		this.newTask = newTask;
	}

	/**
	 * @return the selectedTasks
	 */
	public Task[] getSelectedTasks() {
		return selectedTasks;
	}

	/**
	 * @param selectedTasks the selectedTasks to set
	 */
	public void setSelectedTasks(final Task[] selectedTasks) {
		this.selectedTasks = selectedTasks;
	}
	
}
