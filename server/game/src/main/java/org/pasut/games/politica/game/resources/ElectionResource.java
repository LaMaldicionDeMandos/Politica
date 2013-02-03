package org.pasut.games.politica.game.resources;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
	@Path("new/{size}/{life}")
	public Election newElection(User user, @PathParam("size") int size, @PathParam("life") int life){
		log.info(String.format("Se crea una nueva Eleccion, el dueño es %1s, el tamaño es %2d y dura %3d semanas", new Object[]{user.getCode(), size, life}));
		return service.newElection(user, size, life);
	}
	
	@GET
	@Path("available")
	public List<Election> searchAvailable(){
		return service.searchAvailable(new Date().getTime());
	}
	
	@POST
	@Path("myAvailable")
	public List<Election> searchAvailable(User owner){
		return service.searchAvailable(new Date().getTime(), owner);
	}
}
