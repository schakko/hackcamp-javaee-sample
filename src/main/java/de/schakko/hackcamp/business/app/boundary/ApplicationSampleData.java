package de.schakko.hackcamp.business.app.boundary;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import de.schakko.hackcamp.business.blog.boundary.BlogPostService;
import de.schakko.hackcamp.business.user.boundary.UserManagement;

@Startup
@Singleton
public class ApplicationSampleData {
	@EJB
	UserManagement userManagement;
	
	@EJB
	BlogPostService blogPostService;

	@PostConstruct
	public void createSampleData() {
		userManagement.createUser("username", "password");
		
		blogPostService.create("Hallo Welt", "Dies ist ein Text");
		blogPostService.create("Hallo Welt 2.", "Dies ist ein 2. Text");
		
	}
}
