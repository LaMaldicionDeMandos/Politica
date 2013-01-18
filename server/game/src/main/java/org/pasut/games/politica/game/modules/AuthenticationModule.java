package org.pasut.games.politica.game.modules;

import org.pasut.games.politica.game.authentication.FacebookAuthServletFilter;

import com.google.inject.servlet.ServletModule;

public class AuthenticationModule extends ServletModule {

	@Override
	protected void configureServlets() {
//		filter("/rest/facebook/").through(FacebookAuthServletFilter.class);
//		bind(FacebookMobileAuthenticationResource.class);
//		bind(FacebookMobileIndexResource.class);
	}

}
