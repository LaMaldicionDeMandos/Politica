package org.pasut.games.politica.game.authentication;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DefaultFacebookAuthenticationService implements
		FacebookAuthenticationService {
	private final DecriptService decript;
	private final Gson gson;
	
	
	@Inject
	public DefaultFacebookAuthenticationService(DecriptService decript, Gson gson){
		this.decript = decript;
		this.gson = gson;
	}

	public FacebookUser getFacebookUser(String encryptedSignedRequest) {
		String decryptedRequest = decript.decript(encryptedSignedRequest);
		return gson.fromJson(decryptedRequest, FacebookUser.class);
	}

	public String getAuthRedirectContent() {
		// TODO Auto-generated method stub
		return null;
	}

	public FacebookConfiguration getConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

}
