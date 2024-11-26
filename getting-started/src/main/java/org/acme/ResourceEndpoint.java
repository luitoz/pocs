package org.acme;


import org.eclipse.microprofile.config.Config;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/resources")
public class ResourceEndpoint {
	

    @GET
    @Path("/{id}")
    public Response getResource(@PathParam("id") String id) {
//    	System.out.println(config.getValue("resourceNotFound", String.class));
        if (id == null || id.isBlank()) {
            throw new InvalidInputException();
        }

        // Simulate a condition where the resource is not found
        if ("notfound".equals(id)) {
            throw new ResourceNotFoundException(id);
        }

        // Simulate a condition where access is denied
        if ("denied".equals(id)) {
            throw new AccessDeniedException();
        }

        return Response.ok("Resource with ID " + id).build();
    }
}

