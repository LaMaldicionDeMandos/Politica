package org.pasut.games.politica.game.resources;

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
	@Path("new/{size}")
	public Election newElection(User user, @PathParam("size") int size){
		log.info(String.format("Se crea una nueva Eleccion, el dueño es %1s, el tamaño es %2d", new Object[]{user.getCode(), size} ));
		return service.newElection(user, size);
	}
}
