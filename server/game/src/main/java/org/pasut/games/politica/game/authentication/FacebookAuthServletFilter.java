package org.pasut.games.politica.game.authentication;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang.StringUtils;
import org.pasut.games.politica.game.domain.User;
import org.pasut.games.politica.game.domain.UserPlatform;
import org.pasut.games.politica.game.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class FacebookAuthServletFilter implements Filter {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(FacebookAuthServletFilter.class);
	
	private final FacebookAuthenticationService facebookService;
	private final UserService userService;
	
	@Inject
	public FacebookAuthServletFilter(FacebookAuthenticationService facebookService, UserService userService){
		this.facebookService = facebookService;
		this.userService = userService;
	}

	public void init(FilterConfig filterConfig) throws ServletException {}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		validateFacebookPermissions(request, response, chain);
	}
	
	private void validateFacebookPermissions(ServletRequest request,
			ServletResponse response, FilterChain chain) throws IOException, ServletException{
		final String encryptedSignedRequest = request.getParameter(FacebookAuthenticationService.SIGNED_REQUEST_KEY);
		if(!StringUtils.isEmpty(encryptedSignedRequest)){
			LOGGER.info("validating facebook permissions...");
			FacebookUser user = facebookService.getFacebookUser(encryptedSignedRequest);
			request.setAttribute("facebookUser", user);
			validateUserOrPermissions(request, response, chain, user);
			return;
		}
		continueFilterChain(request, response, chain);		
	}
	
	private void validateUserOrPermissions(ServletRequest request,
			ServletResponse response, FilterChain chain,
			FacebookUser facebookUser) throws IOException, ServletException {
		if (StringUtils.isEmpty(facebookUser.getUser_id())) {
			redirectUser(response);
			return;
		}
		User user = null;
		try{
			user = userService.findUserByPlatform(facebookUser.getUser_id(), UserPlatform.FACEBOOK);		
		}catch(UserNotFoundException e){
			user = userService.addNewUser(	UserPlatform.FACEBOOK,
											facebookUser.getUser_id(), 
											facebookUser.getUser().getCountry(), 
											facebookUser.getUser().getLocale());
			user.setIsNew(true);
		}
		finally{
			request.setAttribute("user", user);
			continueFilterChain(request, response, chain);			
		}
	}
	
	private void redirectUser(ServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		writer.write(facebookService.getAuthRedirectContent());
		response.setContentType("text/html");
	}
	
	private void continueFilterChain(ServletRequest request,
			ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setAttribute("facebook_api_key", facebookService.getConfiguration().getFacebookApiKey());
		chain.doFilter(request, response);
	}

	public void destroy() {}

}
