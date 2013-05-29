package org.esupportail.example.web.beans;

import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Sort.Direction;

public class Conversions {
	
	public static Direction sortOrder2Direction(final SortOrder sortOrder) {
		if (SortOrder.DESCENDING.equals(sortOrder)) {
			return Direction.DESC;
		}
		return Direction.ASC;
	}
	
}
