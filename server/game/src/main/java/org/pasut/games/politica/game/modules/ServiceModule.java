package org.pasut.games.politica.game.modules;

import org.pasut.games.politica.game.service.DefaultElectionsService;
import org.pasut.games.politica.game.service.DefaultUserService;
import org.pasut.games.politica.game.service.ElectionsService;
import org.pasut.games.politica.game.service.UserService;

import com.google.inject.AbstractModule;

public class ServiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(UserService.class).to(DefaultUserService.class);
		bind(ElectionsService.class).to(DefaultElectionsService.class);
	}

}
