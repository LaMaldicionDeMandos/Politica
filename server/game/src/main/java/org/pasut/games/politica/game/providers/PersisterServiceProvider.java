package org.pasut.games.politica.game.providers;

import org.pasut.persister.MongoPersisterService;
import org.pasut.persister.PersisterService;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class PersisterServiceProvider implements Provider<PersisterService> {
	private final String dbHost;
	private final String dbName;
	private final int dbPort;
	
	@Inject
	public PersisterServiceProvider(@Named("db.host") String dbHost, 
									@Named("db.name") String dbName,
									@Named("db.port") int dbPort){
		this.dbHost = dbHost;
		this.dbName = dbName;
		this.dbPort = dbPort;
		
	}
	
	@Override
	public PersisterService get() {
		return new MongoPersisterService(dbName, dbHost, dbPort);
	}

}
