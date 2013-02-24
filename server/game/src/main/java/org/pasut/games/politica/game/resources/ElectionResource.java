package org.pasut.games.politica.game.resources;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.pasut.games.politica.game.domain.Election;
import org.pasut.games.politica.game.domain.User;
import org.pasut.games.politica.game.service.ElectionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Path("election")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class ElectionResource {
	private static Logger log = LoggerFactory.getLogger(ElectionResource.class);
	
	private final ElectionsService service; 
	
	@Inject
	public ElectionResource(ElectionsService service){
		this.service = service;
	}
	
	@POST
	@Path("new/{size}/{life}/{name}")
	public Election newElection(User user, @PathParam("size") int size, @PathParam("life") int life, @PathParam("name") String name){
		log.info(String.format("Se crea una nueva Eleccion, el dueño es %1s, el tamaño es %2d y dura %3d semanas", new Object[]{user.getCode(), size, life}));
		return service.newElection(user, name, size, life);
	}
	
	@POST
	@Path("available")
	public List<Election> searchAvailable(User user){
		return service.searchAvailableWithoutUser(new Date().getTime(), user);
	}
	
	@POST
	@Path("myAvailable")
	public List<Election> searchMyAvailable(User owner){
		return service.searchMyAvailable(new Date().getTime(), owner);
	}
	
	@POST
	@Path("myActive")
	public List<Election> searchMyActive(User user){
		return service.searchActiveWithUser(new Date().getTime(), user);
	}
	
	@POST
	@Path("active/{id}")
	public Election active(User user, @PathParam("id") String id){
		Election election = service.findElection(id);
		throwIfNotValid(user, election);
		return service.activateElection(election);
	}
	
	private void throwIfNotValid(User user, Election election){
		if(!service.validateOwner(user, election)) 
			throw new IllegalArgumentException(String.format("The User %1s not is owner of election %2s", user.toString(), election.toString()));
	}
}
