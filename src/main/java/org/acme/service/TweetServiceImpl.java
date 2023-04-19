package org.acme.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.acme.model.Tweet;
import org.acme.model.User;
import org.bson.Document;
import org.bson.types.ObjectId;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TweetServiceImpl implements TweetService {

    @Inject
    MongoClient mongoClient;

    private MongoCollection getCollection(){
        return mongoClient.getDatabase("ClusterArep").getCollection("tweet");
    }

    @Override
    public List<Tweet> getAllTweet() {
        List<Tweet> list = new ArrayList<>();
        MongoCursor<Document> cursor = getCollection().find().iterator();
        try {
            while (cursor.hasNext()) {
                Document tweetDocument = cursor.next();
                Tweet tweet = new Tweet();
                tweet.setId(String.valueOf(tweetDocument.getObjectId("_id")));
                tweet.setTexto(tweetDocument.getString("texto"));
                tweet.setDate(tweetDocument.getDate("date"));
                User userTweet = new User();
                Document documentUser = (Document) tweetDocument.get("user");
                userTweet.setId(documentUser.getString("_id"));
                userTweet.setName(documentUser.getString("name"));
                userTweet.setEmail(documentUser.getString("email"));
                userTweet.setPassword(documentUser.getString("password"));
                tweet.setUser(userTweet);
                list.add(tweet);
            }
        } finally {
            cursor.close();
        }
        return list;
    }

    @Override
    public Optional<Tweet> getTweetById(String id) {
        ObjectId objectId = new ObjectId(id);
        Document tweetDocument = (Document) getCollection().find(new Document("_id", objectId)).first();
        Tweet tweet = new Tweet();
        tweet.setId(String.valueOf(tweetDocument.getObjectId("_id")));
        tweet.setTexto(tweetDocument.getString("texto"));
        tweet.setDate(tweetDocument.getDate("date"));
        User userTweet = new User();
        Document documentUser = (Document) tweetDocument.get("user");
        userTweet.setId(documentUser.getString("_id"));
        userTweet.setName(documentUser.getString("name"));
        userTweet.setEmail(documentUser.getString("email"));
        userTweet.setPassword(documentUser.getString("password"));
        tweet.setUser(userTweet);
        return Optional.of(tweet);
    }

    @Override
    public void createTweet(Tweet tweet) {
        Document document = new Document()
                .append("user", tweet.getUser())
                .append("texto", tweet.getTexto())
                .append("date", tweet.getDate());
        getCollection().insertOne(document);
    }

    @Override
    public void updateTweet(String id, Tweet tweetNew) {
        MongoCollection<Tweet> collection = mongoClient.getDatabase("ClusterArep").getCollection("tweet", Tweet.class);
        Tweet tweet = collection.find(Filters.eq("_id", new ObjectId(id))).first();
        if (tweet != null) {
            tweet.setUser(tweetNew.getUser());
            tweet.setTexto(tweetNew.getTexto());
            tweet.setDate(tweetNew.getDate());
            //collection.replaceOne(Filters.eq("_id", new ObjectId(id)), user);
        }
    }

    @Override
    public void deleteTweet(String id) {
        ObjectId objectId = new ObjectId(id);
        getCollection().deleteOne(Filters.eq("_id", objectId));
    }
}
