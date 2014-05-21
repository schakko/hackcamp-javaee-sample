package de.schakko.hackcamp.business.user.boundary;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.schakko.hackcamp.business.user.entity.User;

/**
 * Authenticates users
 * 
 * @author ckl
 */
@Stateless
public class UserAuthentication {
	@PersistenceContext
	EntityManager em;

	@EJB
	UserManagement userManagement;

	public User authenticate(String username, String password) throws AuthenticationException {
		try {
			User user = userManagement.findUserByUsername(username);

			if (user != null && user.getPassword() != null && user.getPassword().equals(password)) {
				return user;
			}
		} catch (Exception e /* NoResultException */) {
		}

		throw new AuthenticationException();

	}
}
