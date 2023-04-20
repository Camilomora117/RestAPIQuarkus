package org.acme.controller;

import com.google.gson.Gson;
import io.quarkus.security.Authenticated;
import org.acme.model.Tweet;
import org.acme.service.TweetService;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/tweet")
public class TweetController {

    @Inject
    TweetService tweetService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTweets() {
        List<Tweet> tweets = tweetService.getAllTweet();
        Gson gson = new Gson();
        String json = gson.toJson(tweets);
        return json;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTweetById(String id) {
        Optional<Tweet> tweet = tweetService.getTweetById(id);
        Gson gson = new Gson();
        if (tweet.isPresent()) {
            return gson.toJson(tweet.get());
        } else {
            throw new NotFoundException();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Authenticated
    public Response addTweet(Tweet tweet) {
        tweetService.createTweet(tweet);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Authenticated
    public Response updateTweet(String id, Tweet tweet) {
        tweetService.updateTweet(id, tweet);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public Response deleteUser(String id) {
        tweetService.deleteTweet(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
