package org.pasut.games.politica.game.authentication;

import org.apache.commons.lang.builder.ToStringBuilder;

public class FacebookUserLocale {
	private String locale;
	private String country;
	
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this).toString();
	}
}
