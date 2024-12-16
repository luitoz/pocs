package org.acme.opentelemetry;


import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class ResourceClient {
	private static final Logger LOG = Logger.getLogger(ResourceClient.class);
	
    @Inject
    MyService myService;
    
    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
    	LOG.info("Hello");
        LOG.info("User login: username=admin, password=secret");
        LOG.info("User SSN: ssn=123-45-6789");
        LOG.info("Credit card number: creditcard=4111111111111111");
    	return myService.getMessage();
    }
}