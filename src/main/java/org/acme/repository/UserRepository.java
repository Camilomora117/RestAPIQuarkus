package org.acme.repository;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.acme.model.User;

@ApplicationScoped
public class UserRepository implements PanacheMongoRepository<User> {
}
