package org.pasut.games.politica.game.authentication;

import org.apache.commons.lang.builder.ToStringBuilder;

public class FacebookUser {
	private String user_id;
	private String algorithm;
	private String issued_at;
	private String oauth_token;
	private Long expires;
	private FacebookUserLocale user;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	public String getIssued_at() {
		return issued_at;
	}
	public void setIssued_at(String issued_at) {
		this.issued_at = issued_at;
	}
	public String getOauth_token() {
		return oauth_token;
	}
	public void setOauth_token(String oauth_token) {
		this.oauth_token = oauth_token;
	}
	public Long getExpires() {
		return expires;
	}
	public void setExpires(Long expires) {
		this.expires = expires;
	}
	public FacebookUserLocale getUser() {
		return user;
	}
	public void setUser(FacebookUserLocale user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this).toString();
	}
}
