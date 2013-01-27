package org.pasut.games.politica.game.service;

import org.pasut.games.politica.game.domain.Election;
import org.pasut.games.politica.game.domain.User;
import org.pasut.persister.PersisterService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DefaultElectionsService implements ElectionsService {
	private final PersisterService db;
	
	@Inject
	public DefaultElectionsService(PersisterService db){
		this.db = db;
	}
	@Override
	public Election newElection(User owner, int size) {
		Election election = new Election(owner, size);
		return db.insert(election);
	}

}
