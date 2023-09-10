package com.example.authorization_service;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    private final Map<String, User> users = new HashMap<>() {{
        put("user1", new User("user1", List.of(Authorities.READ)));
        put("user2", new User("user2", List.of(Authorities.WRITE, Authorities.DELETE)));
        put("admin", new User("admin", List.of(Authorities.READ, Authorities.WRITE, Authorities.DELETE)));
        put("guest_user", new User("guest_user", List.of()));
    }};

    public List<Authorities> getUserAuthorities(String username, String password) {
        User user = users.get(username);
        if (user == null || !user.password.equals(password)) {
            throw new InvalidCredentials("Incorrect username or password");
        }
        return user.authorities;
    }

    private static class User {
        String password;
        List<Authorities> authorities;

        public User(String password, List<Authorities> authorities) {
            this.password = password;
            this.authorities = authorities;
        }
    }
}