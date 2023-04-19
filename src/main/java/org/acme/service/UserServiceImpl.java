package org.acme.service;

import javax.inject.Inject;

import com.mongodb.client.model.Filters;
import org.acme.model.User;
import org.bson.types.ObjectId;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService{

    @Inject MongoClient mongoClient;

    private MongoCollection getCollection(){
        return mongoClient.getDatabase("ClusterArep").getCollection("user");
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        MongoCursor<Document> cursor = getCollection().find().iterator();
        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                User user = new User();
                user.setId(String.valueOf(document.getObjectId("_id")));
                user.setName(document.getString("name"));
                user.setEmail(document.getString("email"));
                user.setPassword(document.getString("password"));
                list.add(user);
            }
        } finally {
            cursor.close();
        }
        return list;
    }

    @Override
    public Optional<User> getUserById(String id) {
        ObjectId objectId = new ObjectId(id);
        Document userDocument = (Document) getCollection().find(new Document("_id", objectId)).first();
        User user = new User();
        user.setId(String.valueOf(userDocument.getObjectId("_id")));
        user.setName(userDocument.getString("name"));
        user.setEmail(userDocument.getString("email"));
        user.setPassword(userDocument.getString("password"));
        return Optional.of(user);
    }

    @Override
    public void createUser(User user) {
        Document document = new Document()
                .append("name", user.getName())
                .append("email", user.getEmail())
                .append("password", user.getPassword());
        getCollection().insertOne(document);
    }

    @Override
    public void updateUser(String id, User userNew) {
        MongoCollection<User> collection = mongoClient.getDatabase("ClusterArep").getCollection("user", User.class);
        User user = collection.find(Filters.eq("_id", new ObjectId(id))).first();
        if (user != null) {
            user.setName(userNew.getName());
            user.setEmail(userNew.getEmail());
            user.setPassword(userNew.getPassword());
            //collection.replaceOne(Filters.eq("_id", new ObjectId(id)), user);
        }
    }

    @Override
    public void deleteUser(String id) {
        ObjectId objectId = new ObjectId(id);
        getCollection().deleteOne(Filters.eq("_id", objectId));
    }
}
