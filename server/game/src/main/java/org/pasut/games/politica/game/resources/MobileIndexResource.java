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

import com.google.inject.Singleton;

@Singleton
@Path("/mobile")
public class MobileIndexResource {
	@GET
	@POST
	public void getIndex(@HeaderParam("User-Agent") String userAgent, @Context HttpServletRequest req,
			@Context HttpServletResponse res) throws ServletException, IOException{
		req.getRequestDispatcher("/movile-index.html").forward(req, res);
	}
}
