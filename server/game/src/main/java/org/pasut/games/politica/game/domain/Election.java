package org.pasut.games.politica.game.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;
import org.pasut.persister.Entity;

@Entity("elections")
public class Election {
	@JsonProperty("_id")
	private String id;
	
	
	private User owner;
	private User[] users; 
	
	public Election(User owner){
		this.owner = owner;
		this.users = new User[]{owner};
	}

	public User getOwner(){
		return owner;
	}
	
	public User[] getUsers(){
		return users;
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Election)) return false;
		Election e = (Election)o;
		return (e.id == null && id == null && e.owner.equals(owner)
				|| e.id.equals(id)
				) ;
	}
	
	@Override
	public int hashCode(){
		return owner.hashCode();
	}
	
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this).toString();
	}
}
