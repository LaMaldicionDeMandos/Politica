package org.pasut.games.politica.game.service;

import org.pasut.games.politica.game.authentication.UserNotFoundException;
import org.pasut.games.politica.game.domain.User;
import org.pasut.games.politica.game.domain.UserPlatform;
import org.pasut.persister.PersisterService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DefaultUserService implements UserService {
	private final PersisterService db;
	
	@Inject
	public DefaultUserService(PersisterService db){
		this.db = db;
	}
	
	@Override
	public User findUserByPlatform(String code, UserPlatform platform) throws UserNotFoundException{
		User example = new User(code);
		example.setPlatform(platform);
		User user = db.findOne(example, new String[]{"code", "platform"});
		if(user==null) throw new UserNotFoundException();
		return user;
	}

	@Override
	public User addNewUser(UserPlatform platform, String code, String country, String locale) {
		User user = new User(code, true);
		user.setCountry(country);
		user.setLocale(locale);
		user.setPlatform(platform);
		db.insert(user);
		return user;
	}

}
