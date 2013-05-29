package org.esupportail.example.web.beans;

import static org.esupportail.example.web.beans.Conversions.sortOrder2Direction;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.esupportail.example.domain.beans.User;
import org.esupportail.example.domain.services.UserService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

@Named
public class UserLazyDataModel extends LazyDataModel<User> {

	/**
	 * Serialization id.
	 */
	private static final long serialVersionUID = 1241413015418850120L;

	/**
	 * Search result.
	 */
	private Page<User> page;
	
	/**
	 * The service.
	 */
	@Inject
	private UserService service;
	
	@Override
	public List<User> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		final int pageNumber = Double.valueOf(Math.ceil(first / pageSize)).intValue();
		final Sort sort = sortField != null ? new Sort(sortOrder2Direction(sortOrder), sortField) : null;
		
		page = service.getAllUsers(pageNumber, pageSize, sort);
		
		this.setRowCount(Long.valueOf(page.getTotalElements()).intValue());
		return page.getContent();
	}
	
	@Override
	public Object getRowKey(final User user) {
		return user.getId();
	}

	@Override
	public User getRowData(final String rowKey) {
		for (User user : page) {
			if (user.getId().equals(rowKey))
				return user;
		}
		return null;
	}
}
