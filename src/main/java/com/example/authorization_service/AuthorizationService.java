package com.example.authorization_service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizationService {
    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Authorities> getAuthorities(String userName, String userPassword) {
        validateCredentials(userName, userPassword);
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(userName, userPassword);
        validateAuthorities(userAuthorities, userName);
        return userAuthorities;
    }

    private void validateCredentials(String userName, String userPassword) {
        if (isEmpty(userName) || isEmpty(userPassword)) {
            throw new InvalidCredentials("Invalid data. Username or password is empty.");
        }
    }

    private void validateAuthorities(List<?> authorities, String userName) {
        if (isEmpty(authorities)) {
            throw new UnauthorizedUser(userName + " is unknown.");
        }
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }
}