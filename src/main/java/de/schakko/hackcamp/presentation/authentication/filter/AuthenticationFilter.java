package de.schakko.hackcamp.presentation.authentication.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.schakko.hackcamp.presentation.authentication.UserSession;

/**
 * This is a simple authentication filter. Which allows unauthenticated useres
 * only to access /resources and /login.xhtml
 * 
 * @author ckl
 * 
 */
@WebFilter("/*")
public class AuthenticationFilter implements Filter {
	private static final String PROTECTED_PATH = "/app/";

	private final static String LOGIN_SITE = "/index.xhtml";

	@Inject
	private UserSession userSession;

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;

		boolean sendRedirect = true;

		// user already logged in
		if (null != userSession.getCurrentUser()) {
			sendRedirect = false;
		}

		String requestURI = request.getRequestURI();
		String contextPath = request.getServletContext().getContextPath();

		// free available resources
		if (!requestURI.contains(PROTECTED_PATH)) {
			sendRedirect = false;
		}

		// user is not logged in, redirect to login site
		if (sendRedirect) {
			response.sendRedirect(contextPath + LOGIN_SITE);
			return;
		}

		arg2.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
	}
}