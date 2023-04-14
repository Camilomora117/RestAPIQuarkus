package org.acme.service;

import com.oracle.svm.core.annotate.Inject;
import org.acme.model.User;
import org.acme.repository.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService{

    @Inject
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    @Override
    public void createUser(User user) {
        userRepository.createUser(user);
    }

    @Override
    public void updateUser(int id, User user) {
        User existingUser = getUserById(id);
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        userRepository.createUser(existingUser);
    }

    @Override
    public void deleteUser(int id) {
        User user = getUserById(id);
        if (user != null) {
            userRepository.deleteUser(id);
        }
    }
}
