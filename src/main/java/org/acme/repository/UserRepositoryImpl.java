package org.acme.repository;

import org.acme.model.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository{

    ArrayList<User> users = new ArrayList<>();

    public UserRepositoryImpl() {
        User user = new User(1, "Camilo", "camilo@mail.com", "12345");
        users.add(user);
    }

    @Override
    public User getUserById(int id) {
        for (User u: users) {
           if (u.getId() == id) return u;
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public void createUser(User user) {
        users.add(user);
    }

    @Override
    public void updateUser(int id, User user) {

    }

    @Override
    public void deleteUser(int id) {

    }
}
