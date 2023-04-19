package org.acme.service;

import javax.inject.Inject;
import org.acme.model.User;
import org.bson.types.ObjectId;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;


import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService{

    @Inject MongoClient mongoClient;

    private MongoCollection getCollection(){
        return mongoClient.getDatabase("ClusterArep").getCollection("user");
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        MongoCursor<Document> cursor = getCollection().find().iterator();

        try {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                User user = new User();
                user.setName(document.getString("id"));
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
    public User getUserById(ObjectId id) {
        return null;
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }
}
