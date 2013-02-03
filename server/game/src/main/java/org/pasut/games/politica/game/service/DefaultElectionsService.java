package org.pasut.games.politica.game.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.pasut.games.politica.game.domain.Election;
import org.pasut.games.politica.game.domain.ElectionState;
import org.pasut.games.politica.game.domain.User;
import org.pasut.persister.PersisterService;
import org.pasut.persister.operators.And;
import org.pasut.persister.operators.Equal;
import org.pasut.persister.operators.GreaterOrEqualThan;
import org.pasut.persister.operators.Or;

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
	public Election newElection(User owner, int size, int life) {
		Election election = new Election(owner, size, life);
		return db.insert(election);
	}
	@Override
	public List<Election> searchAvailable(long date) {
		List<Election> availableElections = db.find(Election.class, new Or(new Equal("state", ElectionState.INIT.toString()), new GreaterOrEqualThan("finalizeDate", date)));
		CollectionUtils.filter(availableElections, new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				return !((Election)object).isFull();
			}
		});
		return availableElections;
	}
	
	@Override
	public List<Election> searchAvailable(long date, User owner) {
		List<Election> availableElections = db.find(Election.class, new And(
				new Or(new Equal("state", ElectionState.INIT), new GreaterOrEqualThan("finalizeDate", date)),
				new Equal("owner", owner)));
		CollectionUtils.filter(availableElections, new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				return !((Election)object).isFull();
			}
		});
		return availableElections;
	}

}
