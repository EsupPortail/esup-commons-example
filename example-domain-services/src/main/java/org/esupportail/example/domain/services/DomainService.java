package org.esupportail.example.domain.services;

import java.io.Serializable;

public interface DomainService<T> extends Serializable {

	T add(T type);
	
	T update(T type);
	
	void delete(T type);
}
