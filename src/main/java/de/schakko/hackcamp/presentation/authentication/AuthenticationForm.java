package de.schakko.hackcamp.presentation.authentication;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;

import de.schakko.hackcamp.business.user.boundary.AuthenticationException;
import de.schakko.hackcamp.business.user.boundary.UserAuthentication;
import de.schakko.hackcamp.business.user.entity.User;

@Named("authenticationBean")
@SessionScoped
public class AuthenticationForm implements Serializable {

	private static final long serialVersionUID = 3037863179722779475L;

	@Inject
	private FacesContext context;

	@Inject
	private UserSession userSession;

	@EJB
	private UserAuthentication userAuthentication;

	private String username;
	private String password;

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public void beforeView(ComponentSystemEvent event) {
		if (userSession.getCurrentUser() != null) {
			ConfigurableNavigationHandler navigationHandler = (ConfigurableNavigationHandler) context.getApplication()
					.getNavigationHandler();

			navigationHandler.performNavigation("/app/index.xhtml");
		}
	}

	public String doLogin()  throws IOException, ServletException {
		try {
			User u = userAuthentication.authenticate(getUsername(), getPassword());

			userSession.setCurrentUser(u);
			return "/app/index.xhtml";
		} catch (AuthenticationException e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login fehlgeschlagen",
					"Ungültiger Benutzername oder Passwort");
			context.addMessage("username", msg);
		}

		return "index";
	}
}
