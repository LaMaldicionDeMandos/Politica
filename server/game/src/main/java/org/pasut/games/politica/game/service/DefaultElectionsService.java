package org.pasut.games.politica.game.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
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
	
	private void activeElections(List<Election> elections){
		final Date date = new Date();
		CollectionUtils.forAllDo(elections, new Closure() {
			@Override
			public void execute(Object input) {
				Election election = (Election)input;
				if(election.readyToActivate(date.getTime())){
					election.activate(date.getTime());
				}
			}
		});
	}
	
	private void endElections(List<Election> elections){
		final Date date = new Date();
		CollectionUtils.forAllDo(elections, new Closure() {
			@Override
			public void execute(Object input) {
				Election election = (Election)input;
				if(election.readyToEnd(date.getTime())){
					election.end(date.getTime());
				}
			}
		});
	}
	
	@Override
	public Election newElection(User owner, int size, int life) {
		Election election = new Election(owner, size, life);
		return db.insert(election);
	}

	@Override
	public List<Election> searchAvailableWithoutUser(long date, User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Election> searchAvailableWithUser(long date, User owner) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Election> searchActiveWithUser(long date, User owner) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Election> searchMyAvailable(long date, User owner) {
		// TODO Auto-generated method stub
		return null;
	}
}
