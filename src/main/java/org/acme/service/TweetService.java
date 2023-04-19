package org.acme.service;

import org.acme.model.Tweet;
import java.util.List;
import java.util.Optional;

public interface TweetService {

    List<Tweet> getAllTweet();

    Optional<Tweet> getTweetById(String id);

    void createTweet(Tweet tweet);

    void updateTweet(String id, Tweet tweet);

    void deleteTweet(String id);
}
