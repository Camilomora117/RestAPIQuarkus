package org.acme.service;

import org.acme.model.User;
import org.bson.types.ObjectId;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(ObjectId id);

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
