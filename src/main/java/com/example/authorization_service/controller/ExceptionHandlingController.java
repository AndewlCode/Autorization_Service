package com.example.authorization_service.controller;

import com.example.authorization_service.InvalidCredentials;
import com.example.authorization_service.UnauthorizedUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> handleUnauthorizedUser(UnauthorizedUser e) {

        System.out.println("Unknown user " + e.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<String> handleInvalidCredentials(InvalidCredentials e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}