package org.mobile.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.Optional;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HelloResource {

    @GET
    public String sayHi(@QueryParam("name") Optional<String> greeting){
        return "Saying hi to " + greeting.orElse("No one");
    }
}
