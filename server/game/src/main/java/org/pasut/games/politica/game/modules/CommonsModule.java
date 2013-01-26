package org.pasut.games.politica.game.modules;

import org.pasut.games.politica.game.providers.PersisterServiceProvider;
import org.pasut.persister.PersisterService;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;

public class CommonsModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Gson.class).toInstance(new Gson());
		bind(PersisterService.class).toProvider(PersisterServiceProvider.class);
	}

}
