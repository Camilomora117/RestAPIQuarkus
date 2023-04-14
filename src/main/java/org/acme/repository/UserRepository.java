package org.acme.repository;

import org.acme.model.User;

import java.util.List;

public interface UserRepository {

    User getUserById(int id);

    List<User> getAllUsers();

    void createUser(User user);

    void updateUser(int id, User user);

    void deleteUser(int id);
}
