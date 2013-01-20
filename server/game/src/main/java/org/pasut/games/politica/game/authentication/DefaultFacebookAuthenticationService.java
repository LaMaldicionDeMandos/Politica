package org.pasut.games.politica.game.authentication;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class DefaultFacebookAuthenticationService implements
		FacebookAuthenticationService {
	private final DecriptService decript;
	private final FacebookConfiguration configuration;
	private final Gson gson;
	
	private String authContent;
	
	
	@Inject
	public DefaultFacebookAuthenticationService(DecriptService decript, FacebookConfiguration configuration, Gson gson){
		this.decript = decript;
		this.configuration = configuration;
		this.gson = gson;
		init();
	}
	
	private void init() {
        try {
        	String facebookRequestdScope = configuration.getFacebookRequestdScope();
            String facebookApiKey = URLEncoder.encode(configuration.getFacebookApiKey(), "UTF-8");
            String url = URLEncoder.encode(configuration.getAppUrl(), "UTF-8");
            String authUrl= String.format("http://www.facebook.com/dialog/oauth?client_id=%s&scope=%s&redirect_uri=%s",
            		facebookApiKey, facebookRequestdScope, url);
            this.authContent = String.format("<script>top.location.href='%s'</script>",authUrl);
        } catch (UnsupportedEncodingException e) {
            throw new CouldNotLoginException(e);
        }
	}

	public FacebookUser getFacebookUser(String encryptedSignedRequest) {
		String decryptedRequest = decript.decript(encryptedSignedRequest);
		return gson.fromJson(decryptedRequest, FacebookUser.class);
	}

	public String getAuthRedirectContent() {
		return authContent;
	}

	public FacebookConfiguration getConfiguration() {
		return configuration;
	}

}
