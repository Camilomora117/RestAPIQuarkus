package org.acme.controller;

import com.google.gson.Gson;
import io.quarkus.security.Authenticated;
import org.acme.model.Stream;
import org.acme.service.StreamService;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/stream")
public class StreamController {

    @Inject
    StreamService streamService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public String getStreams() {
        List<Stream> streams = streamService.getAllStreams();
        Gson gson = new Gson();
        String json = gson.toJson(streams);
        return json;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public String getStreamById(String id) {
        Optional<Stream> stream = streamService.getStreamById(id);
        Gson gson = new Gson();
        if (stream.isPresent()) {
            return gson.toJson(stream.get());
        } else {
            throw new NotFoundException();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Authenticated
    public Response addStream(Stream stream) {
        streamService.createStream(stream);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Authenticated
    public Response updateStream(String id, Stream stream) {
        streamService.updateStream(id, stream);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public Response deleteUser(String id) {
        streamService.deleteStream(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
