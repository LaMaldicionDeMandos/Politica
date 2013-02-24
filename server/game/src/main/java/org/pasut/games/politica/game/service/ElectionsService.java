package org.pasut.games.politica.game.service;

import java.util.List;

import org.pasut.games.politica.game.domain.Election;
import org.pasut.games.politica.game.domain.User;

public interface ElectionsService {
	
	/**
	 * Create a new Election with "owner" as the first user
	 * @param owner The Election owner
	 * @param name Then Election's name
	 * @param size The max Election size
	 * @return Election with Id
	 */
	Election newElection(User owner, String name, int size, int life);
	
	/**
	 * Search all available Election without user, available is not active 
	 * @param date current date
	 * @param user User
	 * @return List of available Election without the current user
	 */
	List<Election> searchAvailableWithoutUser(long date, User user);
	
	/**
	 * Search all available Election with user, available is not active 
	 * @param date current date
	 * @param user User
	 * @return List of available Election with the current user
	 */
	List<Election> searchAvailableWithUser(long date, User user);
	
	/**
	 * Search all active Election with user 
	 * @param date current date
	 * @param user User
	 * @return List of active Election with current user
	 */
	List<Election> searchActiveWithUser(long date, User user);
	
	/**
	 * Search all active Election with user as owner 
	 * @param date current date
	 * @param user User
	 * @return List of active Election with user owner
	 */
	List<Election> searchMyAvailable(long date, User owner);

	/**
	 * find election 
	 * @param id election id
	 * @return Election
	 */
	Election findElection(String id);
	
	/**
	 * Active if is valid the current Election
	 * @Param Election
	 * @return Activated election
	 */
	Election activateElection(Election election);

	/**
	 * Active if is valid the current Election
	 * @Param id election id
	 * @return Activated election
	 */
	Election activateElection(String id);

	/**
	 * Validate if current user is owner of the election
	 * @param user
	 * @param election
	 * @return True if user is owner, false otherwise
	 */
	boolean validateOwner(User user, Election election);
}
