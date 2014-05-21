package de.schakko.hackcamp.business.user.boundary;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.schakko.hackcamp.business.user.entity.User;

/**
 * Service for managing users
 * 
 * @author ckl
 * 
 */
@Stateless
public class UserManagement {
	@PersistenceContext
	EntityManager em;

	/**
	 * Finds a given user by its username
	 * 
	 * @param username
	 * @return
	 */
	public User findUserByUsername(String username) {
		return (User) em.createQuery("SELECT u FROM User u WHERE username = ?").setParameter(1, username)
				.getSingleResult();
	}

	/**
	 * Creates a new user with username and password
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public User createUser(String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		em.persist(user);
		em.flush();

		return user;
	}
}
