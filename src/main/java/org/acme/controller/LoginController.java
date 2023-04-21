package org.acme.controller;

import com.google.gson.Gson;
import org.acme.cognito.CognitoAuth;
import org.acme.model.User;
import org.acme.service.UserService;
import org.jose4j.json.internal.json_simple.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Objects;
import java.util.Optional;

@Path("/login")
public class LoginController {

    @Inject
    UserService userService;

    CognitoAuth cognitoAuth = new CognitoAuth();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String login(User user){
        String response = cognitoAuth.login(user.getEmail(), user.getPassword()).get("message");
        if (Objects.equals(response, "Successfully login")) {
            String token = cognitoAuth.login(user.getEmail(), user.getPassword()).get("idToken");
            User dataUser = userService.getUserByEmail(user.getEmail()).get();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", dataUser.getName());
            jsonObject.put("email", dataUser.getEmail());
            jsonObject.put("token", token);
            return jsonObject.toString();
        } else {
            throw new Error("Credenciales inválidas");
        }
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
