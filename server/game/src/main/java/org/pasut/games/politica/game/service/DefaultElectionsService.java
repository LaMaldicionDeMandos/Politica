package org.pasut.games.politica.game.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.pasut.games.politica.game.domain.Election;
import org.pasut.games.politica.game.domain.ElectionState;
import org.pasut.games.politica.game.domain.User;
import org.pasut.persister.PersisterService;
import org.pasut.persister.operators.And;
import org.pasut.persister.operators.Equal;
import org.pasut.persister.operators.Identiry;
import org.pasut.persister.operators.In;
import org.pasut.persister.operators.NotIn;
import org.pasut.persister.operators.Or;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DefaultElectionsService implements ElectionsService {
	private final Predicate initFilter = new InitElectionFilter();
	private final Predicate activeFilter = new ActiveElectionFilter();
	@SuppressWarnings("unused")
	private final Predicate endFilter = new EndElectionFilter();
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
				if(election.readyToActivate(date.getTime()) && election.getInitDate()!=0){
					election.activate(date.getTime());
					db.update(election);
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
					db.update(election);
				}
			}
		});
	}
	
	@Override
	public Election newElection(User owner, String name, int size, int life) {
		Election election = new Election(owner, name, size, life);
		return db.insert(election);
	}

	@Override
	public List<Election> searchAvailableWithoutUser(long date, User user) {
		List<Election> elections = db.find(Election.class, 
				new And(
						new Equal("state", ElectionState.INIT), 
						new NotIn("users", user)
						)
		);
		activeElections(elections);
		CollectionUtils.filter(elections, initFilter);
		return elections;
	}
	
	@Override
	public List<Election> searchAvailableWithUser(long date, User user) {
		List<Election> elections = db.find(Election.class, 
				new And(
						new Equal("state", ElectionState.INIT), 
						new In("users", user)
						)
		);
		activeElections(elections);
		CollectionUtils.filter(elections, initFilter);
		return elections;
	}
	
	@Override
	public List<Election> searchActiveWithUser(long date, User user) {
		List<Election> elections = db.find(Election.class, 
				new And(
						new Or(
								new Equal("state", ElectionState.INIT), 
								new Equal("state", ElectionState.ACTIVE)
							),
							new In("users", user)
						)
		);
		activeElections(elections);
		endElections(elections);
		CollectionUtils.filter(elections, activeFilter);
		return elections;
	}
	
	@Override
	public List<Election> searchMyAvailable(long date, User owner) {
		List<Election> elections = db.find(Election.class, 
				new And(
						new Equal("state", ElectionState.INIT), 
						new Equal("owner", owner)
						)
		);
		activeElections(elections);
		CollectionUtils.filter(elections, initFilter);
		return elections;
	}
	
	@Override
	public Election activateElection(Election election) {
		election.activate();
		db.update(election);
		return election;
	}

	@Override
	public Election activateElection(String id) {
		Election election = findElection(id);
		return activateElection(election);
	}
	
	@Override
	public boolean validateOwner(User user, Election election){
		return election.isOwner(user);
	}
	
	@Override
	public Election findElection(String id) {
		return db.findOne(Election.class, new Identiry(id));
	}
	
	private class InitElectionFilter implements Predicate{

		@Override
		public boolean evaluate(Object object) {
			Election election = (Election)object;
			return election.getState() == ElectionState.INIT;
		}
		
	}
	private class ActiveElectionFilter implements Predicate{
		
		@Override
		public boolean evaluate(Object object) {
			Election election = (Election)object;
			return election.getState() == ElectionState.ACTIVE;
		}
		
	}
	private class EndElectionFilter implements Predicate{
		
		@Override
		public boolean evaluate(Object object) {
			Election election = (Election)object;
			return election.getState() == ElectionState.ENDED;
		}
		
	}
}
