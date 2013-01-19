package org.pasut.games.politica.game.modules;

import org.pasut.games.politica.game.authentication.DecriptService;
import org.pasut.games.politica.game.authentication.DefaultFacebookAuthenticationService;
import org.pasut.games.politica.game.authentication.FacebookAuthServletFilter;
import org.pasut.games.politica.game.authentication.FacebookAuthenticationService;
import org.pasut.games.politica.game.authentication.FacebookConfiguration;
import org.pasut.games.politica.game.authentication.Sha256DecriptService;

import com.google.inject.servlet.ServletModule;

public class AuthenticationModule extends ServletModule {

	@Override
	protected void configureServlets() {
		filter("/rest/facebook/").through(FacebookAuthServletFilter.class);

		bind(DecriptService.class).to(Sha256DecriptService.class);
		bind(FacebookConfiguration.class);
		bind(FacebookAuthenticationService.class).to(DefaultFacebookAuthenticationService.class);
	}

}
