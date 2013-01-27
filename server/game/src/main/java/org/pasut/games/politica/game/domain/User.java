package org.pasut.games.politica.game.domain;

import org.codehaus.jackson.annotate.JsonProperty;
import org.pasut.persister.Entity;

import com.google.gson.Gson;

@Entity("users")
public class User {
	@JsonProperty("_id")
	private String id;
	
	private String code = "";
	private String country;
	private String locale;
	private UserPlatform platform = UserPlatform.FACEBOOK;
	private boolean isNew;
	
	public User(){}
	
	public User(String code){
		this.code = code;
	}
	
	public User(String code, boolean isNew){
		this.code = code;
		this.isNew = isNew;
	}
	
	public User(boolean isNew){
		
		this.isNew = isNew;
	}
	
	public String getId(){
		return id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code){
		this.code = code;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setIsNew(boolean isNew) {
		this.isNew = isNew;
	}
	
	@Override
	public String toString(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof User)) return false;
		User u = (User)o;
		return this.code.equals(u.code);
	}
	
	@Override
	public int hashCode(){
		return this.code.hashCode();
	}

	public UserPlatform getPlatform() {
		return platform;
	}

	public void setPlatform(UserPlatform platform) {
		this.platform = platform;
	}
	
}
