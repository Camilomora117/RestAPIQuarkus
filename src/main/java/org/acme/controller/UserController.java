package org.acme.controller;

import io.quarkus.security.Authenticated;
import org.acme.model.User;
import org.acme.service.UserService;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import com.google.gson.Gson;


@Path("/user")
public class UserController {

    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public String getUsers() {
        List<User> users = userService.getAllUsers();
        Gson gson = new Gson();
        String json = gson.toJson(users);
        return json;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public String getUserById(String id) {
        Optional<User> user = userService.getUserById(id);
        Gson gson = new Gson();
        if (user.isPresent()) {
            return gson.toJson(user.get());
        } else {
            throw new NotFoundException();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Authenticated
    public Response addUser(User user) {
        userService.createUser(user);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Authenticated
    public Response updateUser(String id, User user) {
        userService.updateUser(id, user);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public Response deleteUser(String id) {
        userService.deleteUser(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}