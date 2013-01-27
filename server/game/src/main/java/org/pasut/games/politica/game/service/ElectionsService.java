package org.pasut.games.politica.game.service;

import org.pasut.games.politica.game.domain.Election;
import org.pasut.games.politica.game.domain.User;

public interface ElectionsService {
	
	/**
	 * Create a new Election with "owner" as the first user
	 * @param owner The Election owner
	 * @param size The max Election size
	 * @return Election with Id
	 */
	Election newElection(User owner, int size);
}
