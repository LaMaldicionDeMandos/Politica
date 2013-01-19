package org.pasut.games.politica.game.authentication;

public interface FacebookAuthenticationService {
	public static final String SIGNED_REQUEST_KEY = "signed_request";
	
	FacebookUser getFacebookUser(String encryptedSignedRequest);
	
	String getAuthRedirectContent();
	
	FacebookConfiguration getConfiguration();
}
