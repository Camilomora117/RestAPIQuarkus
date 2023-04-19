package org.acme.controller;

import org.acme.model.User;
import org.acme.service.UserService;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import com.google.gson.Gson;

@Path("/users")
public class UserController {

    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() {
        List<User> users = userService.getAllUsers();
        Gson gson = new Gson();
        String json = gson.toJson(users);
        return json;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(@PathParam("id") int id) {
        User user = userService.getUserById(id);
        Gson gson = new Gson();
        if (user != null) {
            return gson.toJson(user);
        } else {
            throw new NotFoundException();
        }
    }

    @POST
    @Produces(MediaType.MEDIA_TYPE_WILDCARD)
    @Consumes(MediaType.MEDIA_TYPE_WILDCARD)
    public Response addUser(User user) {
        userService.createUser(user);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") int id, User user) {
        userService.updateUser(id, user);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") int id) {
        userService.deleteUser(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}