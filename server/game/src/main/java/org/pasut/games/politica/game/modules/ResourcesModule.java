package org.pasut.games.politica.game.modules;

import org.pasut.games.politica.game.resources.FacebookIndexResource;
import org.pasut.games.politica.game.resources.MobileIndexResource;

import com.google.inject.AbstractModule;

public class ResourcesModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(FacebookIndexResource.class);
		bind(MobileIndexResource.class);
	}

}
