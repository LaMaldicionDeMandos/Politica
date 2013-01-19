package org.pasut.games.politica.game.authentication;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

@Singleton
public class FacebookConfiguration {
	public static final String KEY = "fb-config";
	private final String facebookApiKey;
	private final String facebookSecret;
	private final String facebookRequestdScope;
	private final String appUrl;

	@Inject
	public FacebookConfiguration(
			@Named("facebook.client.id") String facebookApiKey,
			@Named("facebook.client.secret") String facebookSecret,
			@Named("facebook.app.scope") String facebookRequestdScope,
			@Named("facebook.app.url") String appUrl) {
		this.facebookApiKey = facebookApiKey;
		this.facebookSecret = facebookSecret;
		this.facebookRequestdScope = facebookRequestdScope;
		this.appUrl = appUrl;
	}

	public String getFacebookApiKey() {
		return facebookApiKey;
	}

	public String getFacebookSecret() {
		return facebookSecret;
	}

	public String getFacebookRequestdScope() {
		return facebookRequestdScope;
	}

	public String getAppUrl() {
		return appUrl;
	}
}
