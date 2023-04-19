package org.acme.service;

import org.acme.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserById(String id);

    void createUser(User user);

    void updateUser(String id, User user);

    void deleteUser(String id);
}
