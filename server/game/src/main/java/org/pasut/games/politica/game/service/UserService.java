package org.pasut.games.politica.game.service;

import org.pasut.games.politica.game.authentication.UserNotFoundException;
import org.pasut.games.politica.game.domain.User;
import org.pasut.games.politica.game.domain.UserPlatform;

public interface UserService {
	User findUserByPlatform(String code, UserPlatform platform) throws UserNotFoundException;
	User addNewUser(UserPlatform platform, String code, String country, String locale);
}
