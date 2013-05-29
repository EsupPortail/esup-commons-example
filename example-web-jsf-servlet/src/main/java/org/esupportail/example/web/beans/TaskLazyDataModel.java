package org.esupportail.example.web.beans;

import static org.esupportail.example.web.beans.Conversions.sortOrder2Direction;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.esupportail.example.domain.beans.Task;
import org.esupportail.example.domain.services.TaskService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

@Named
public class TaskLazyDataModel extends LazyDataModel<Task> {

	/**
	 * Serialization id.
	 */
	private static final long serialVersionUID = 4126029064604906173L;
	
	/**
	 * A filter on {@link Task}'s public state.
	 */
	private boolean publicFilter;

	/**
	 * Search result.
	 */
	private Page<Task> page;
	
	@Inject
	private TaskService service;
	
	@Override
	public List<Task> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		final int pageNumber = Double.valueOf(Math.ceil(first / pageSize)).intValue();
		final Sort sort = sortField != null ? new Sort(sortOrder2Direction(sortOrder), sortField) : null;
		if (publicFilter) {
			page = service.getPublicTasks(pageNumber, pageSize, sort);
		} else {
			page = service.getAllTasks(pageNumber, pageSize, sort);
		}
		this.setRowCount(Long.valueOf(page.getTotalElements()).intValue());
		return page.getContent();
	}
	
	@Override
	public Object getRowKey(final Task task) {
		return task.getId();
	}

	@Override
	public Task getRowData(final String rowKey) {
		for (Task task : page) {
			if (task.getId().equals(Long.valueOf(rowKey)))
				return task;
		}
		return null;
	}
	
	/**
	 * @return the publicFilter
	 */
	public boolean getPublicFilter() {
		return publicFilter;
	}

	/**
	 * @param publicFilter the publicFilter to set
	 */
	public void setPublicFilter(final boolean publicFilter) {
		this.publicFilter = publicFilter;
	}

	/**
	 * @param publicFilter the publicFilter to set
	 */
	public TaskLazyDataModel withPublicFilter(final boolean publicFilter) {
		this.publicFilter = publicFilter;
		return this;
	}
}
