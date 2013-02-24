package org.pasut.games.politica.game.domain;

import java.util.Calendar;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;
import org.pasut.persister.Entity;

import com.google.gson.Gson;

@Entity("elections")
public class Election {
	@JsonProperty("_id")
	private String id;
	
	private User owner;
	private User[] users;
	private int size;
	private ElectionState state;
	private int life;
	private long initDate;
	
	
	public Election(User owner, int size, int life){
		if(size<=1) throw new IllegalArgumentException("Election Size must be greatet than 1 and was " + size);
		this.size = size;
		this.owner = owner;
		this.users = new User[size];
		this.addUser(owner);
		this.state = ElectionState.INIT;
		this.life = life;
	}
	
	public boolean isFull(){
		for(User user : users){
			if(user==null) return false;
		}
		return true;
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
	
	public boolean readyToActivate(long date){
		if(state!=ElectionState.INIT) return false;
		return initDate == 0 || initDate <= date;
	}
	
	public boolean readyToActivate(){
		return readyToActivate(new Date().getTime());
	}
	
	public void activate(long date){
		if(readyToActivate(date)){
			state = ElectionState.ACTIVE;
			initDate = date;			
		}
	}
	
	public void activate(){
		activate(new Date().getTime());
	}
	
	public void end(){
		end(new Date().getTime());
	}
	
	public void end(long date){
		if(readyToEnd(date)) state = ElectionState.ENDED;
	}
	
	public boolean readyToEnd(long date){
		if(state!=ElectionState.ACTIVE) return false;
		Calendar calendar = (Calendar)Calendar.getInstance().clone();
		calendar.setTime(new Date(initDate));
		calendar.add(Calendar.WEEK_OF_YEAR, life);
		return date > calendar.getTimeInMillis();
	}
	
	public boolean readyToEnd(){
		return readyToEnd(new Date().getTime());
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
	
	public ElectionState getState(){
		return this.state;
	}
	
	protected void setState(ElectionState state){
		this.state = state;
	}
	
	public int getLife(){
		return this.life;
	}
	
	protected void setLife(int life){
		this.life = life;
	}
	
	public long getInitDate(){
		return initDate;
	}
	
	public void setInitDate(long date){
		this.initDate = date;
	}
	
	public long getFinelizeDate(){
		if(initDate==0) return 0;
		Calendar calendar = (Calendar)Calendar.getInstance().clone();
		calendar.setTime(new Date(initDate));
		calendar.add(Calendar.WEEK_OF_YEAR, life);
		return calendar.getTimeInMillis();
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
		return new Gson().toJson(this);
	}
}
