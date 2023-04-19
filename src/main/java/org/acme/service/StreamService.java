package org.acme.service;

import org.acme.model.Stream;
import java.util.List;
import java.util.Optional;

public interface StreamService {

    List<Stream> getAllStreams();

    Optional<Stream> getStreamById(String id);

    void createStream(Stream stream);

    void updateStream(String id, Stream stream);

    void deleteStream(String id);
}
