package org.pasut.games.politica.game.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.pasut.games.politica.game.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("election")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ElectionResource {
	Logger log = LoggerFactory.getLogger(ElectionResource.class);
	
	@POST
	public void newElection(User user){
		log.info(String.format("Se crea una nueva Eleccion, el dueño es %s", new Object[]{user.getCode()} ));
	}
}
