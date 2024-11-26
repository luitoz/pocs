package org.acme;


import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/users")
public class ResourceEndpoint {
	

	@POST
	public Response validate(@Valid User user) {
	    return Response.ok().build();
	}
}
