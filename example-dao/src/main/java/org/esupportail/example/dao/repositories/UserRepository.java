/**
 * 
 */
package org.esupportail.example.dao.repositories;

import org.esupportail.example.domain.beans.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
	 * Finds admin {@link User}s. 
	 * @return
	 */
	Page<User> findByAdminTrue(Pageable pageable);
}
