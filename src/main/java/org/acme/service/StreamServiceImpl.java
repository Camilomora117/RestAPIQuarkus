package org.acme.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.acme.model.Stream;
import org.acme.model.Tweet;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class StreamServiceImpl implements StreamService{

    @Inject
    MongoClient mongoClient;

    private MongoCollection getCollection(){
        return mongoClient.getDatabase("ClusterArep").getCollection("stream");
    }

    @Override
    public List<Stream> getAllStreams() {
        List<Stream> list = new ArrayList<>();
        MongoCursor<Document> cursor = getCollection().find().iterator();
        try {
            while (cursor.hasNext()) {
                Document streamDocument = cursor.next();
                Stream stream = new Stream();
                stream.setId(String.valueOf(streamDocument.getObjectId("_id")));
                List<Document> tweets = streamDocument.getList("tweetList", Document.class);
                List<Tweet> tweetList = new ArrayList<>();
                tweets.forEach(document -> {
                    Tweet newTweet = new Tweet(document);
                    tweetList.add(newTweet);
                });
                stream.setTweetList(tweetList);
                list.add(stream);
            }
        } finally {
            cursor.close();
        }
        return list;
    }

    @Override
    public Optional<Stream> getStreamById(String id) {
        ObjectId objectId = new ObjectId(id);
        Document streamDocument = (Document) getCollection().find(new Document("_id", objectId)).first();
        Stream stream = new Stream();
        stream.setId(String.valueOf(streamDocument.getObjectId("_id")));
        List<Document> tweets = streamDocument.getList("tweetList", Document.class);
        List<Tweet> tweetList = new ArrayList<>();
        tweets.forEach(document -> {
            Tweet newTweet = new Tweet(document);
            tweetList.add(newTweet);
        });
        stream.setTweetList(tweetList);
        return Optional.of(stream);
    }

    @Override
    public void createStream(Stream stream) {
        Document document = new Document()
                .append("tweetList", stream.getTweetList());
        getCollection().insertOne(document);
    }

    @Override
    public void updateStream(String id, Stream streamNew) {
        MongoCollection<Stream> collection = mongoClient.getDatabase("ClusterArep").getCollection("stream", Stream.class);
        Stream stream = collection.find(Filters.eq("_id", new ObjectId(id))).first();
        if (stream != null) {
            stream.setTweetList(streamNew.getTweetList());
            //collection.replaceOne(Filters.eq("_id", new ObjectId(id)), stream);
        }
    }

    @Override
    public void deleteStream(String id) {
        ObjectId objectId = new ObjectId(id);
        getCollection().deleteOne(Filters.eq("_id", objectId));
    }
}
