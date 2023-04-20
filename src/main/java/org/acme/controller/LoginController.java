package org.acme.controller;

import com.google.gson.Gson;
import org.acme.cognito.CognitoAuth;
import org.acme.model.User;
import org.acme.service.UserService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/login")
public class LoginController {

    @Inject
    UserService userService;

    CognitoAuth cognitoAuth = new CognitoAuth();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String login(User user){
        return cognitoAuth.login(user.getEmail(), user.getPassword()).get("message")+" Token:"+cognitoAuth.login(user.getEmail(), user.getPassword()).get("idToken");
    }

    @POST
    @Path("/sign-up")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String signUp(User user){
        userService.createUser(user);
        Gson gson = new Gson();
        String json = gson.toJson(cognitoAuth.signUp(user.getName(),user.getEmail(),user.getPassword()));
        return json;
    }
}
