package org.pasut.games.politica.game.authentication;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class FacebookAuthServletFilter implements Filter {
	private final FacebookAuthenticationService facebookService;
	
	@Inject
	public FacebookAuthServletFilter(FacebookAuthenticationService facebookService){
		this.facebookService = facebookService;
	}

	public void init(FilterConfig filterConfig) throws ServletException {}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		validateFacebookPermissions(request, response, chain);
	}
	
	private void validateFacebookPermissions(ServletRequest request,
			ServletResponse response, FilterChain chain) throws IOException, ServletException{
		final String encryptedSignedRequest = request.getParameter(FacebookAuthenticationService.SIGNED_REQUEST_KEY);
		FacebookUser user = facebookService.getFacebookUser(encryptedSignedRequest);
		System.out.println(user);
		continueFilterChain(request, response, chain);		
	}
	
	private void continueFilterChain(ServletRequest request,
			ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(request, response);
	}

	public void destroy() {}

}
