package de.schakko.hackcamp.presentation.helper;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 * Produces {@link FacesContext} and can be used for easier testing and getting
 * rid of accessing the static method {@link FacesContext#getCurrentInstance()}
 * 
 * @author ckl
 */
public class FacesProducer {
	@Produces
	@RequestScoped
	FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	@Produces
	@RequestScoped
	Flash getFlashScope() {
		return (FacesContext.getCurrentInstance().getExternalContext().getFlash());
	}
}
