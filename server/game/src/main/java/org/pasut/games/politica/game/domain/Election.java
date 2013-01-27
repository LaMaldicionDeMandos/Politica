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
	private int size;
	
	public Election(User owner, int size){
		if(size<=1) throw new IllegalArgumentException("Election Size must be greatet than 1 and was " + size);
		this.size = size;
		this.owner = owner;
		this.users = new User[size];
		this.addUser(owner);
	}
	
	public void addUser(User user){
		users[getLenght()] = user;
	}
	
	public void removeUser(User user){
		boolean found = false;
		for(int i=0;i<size;i++){
			if(found) users[i-1] = users[i];
			if(users[i]==null) return;
			if(!found && users[i].equals(user)){ 
				found = true;
			}
		}
	}
	
	public String getId(){
		return id;
	}
	
	protected void setId(String id){
		this.id = id;
	}
	
	public int getLenght(){
		int i = 0;
		while(i<size && users[i]!=null)i++;
		return i;
	}

	public User getOwner(){
		return owner;
	}
	
	protected void setOwner(User owner){
		this.owner = owner;
	}
	
	public User[] getUsers(){
		return users;
	}
	
	protected void setUsers(User[] users){
		this.users = users;
	}
	
	public int getSize(){
		return size;
	}
	
	protected void setSize(int size){
		this.size = size;
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
