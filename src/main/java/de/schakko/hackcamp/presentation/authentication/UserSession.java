package de.schakko.hackcamp.presentation.authentication;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import de.schakko.hackcamp.business.user.entity.User;

/**
 * 
 * {@link Serializable} is required for {@link SessionScoped} beans
 * 
 * @author ckl
 * 
 */
@SessionScoped
public class UserSession implements Serializable {
	private static final long serialVersionUID = -8430399977340927778L;
	private User currentUser;

	/**
	 * @return the currentUser
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser
	 *            the currentUser to set
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
}
