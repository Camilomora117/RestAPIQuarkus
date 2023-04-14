package org.acme.resource;

import org.acme.model.User;
import org.acme.service.UserService;
//import javax.validation.Valid;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @GET
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GET
    @Path("/{id}")
    public User getUser(@PathParam("id") int id) {
        return userService.getUserById(id);
    }

//    @POST
//    @Transactional
//    public Response addUser(@Valid User user) {
//        userService.createUser(user);
//        return Response.status(Response.Status.CREATED).build();
//    }

//    @PUT
//    @Path("/{id}")
//    @Transactional
//    public Response updateUser(@PathParam("id") int id, @Valid User user) {
//        userService.updateUser(id, user);
//        return Response.status(Response.Status.NO_CONTENT).build();
//    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteUser(@PathParam("id") int id) {
        userService.deleteUser(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}