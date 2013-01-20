package org.pasut.games.politica.game.resources;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

@Singleton
@Path("/facebook")
public class FacebookIndexResource {
	private final String index;
	
	@Inject
	public FacebookIndexResource(@Named("facebook.index") String index){
		this.index = index;
	}
	
	@GET
	@POST
	public void getIndex(@HeaderParam("User-Agent") String userAgent, @Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher(index).forward(request, response);
	}
}