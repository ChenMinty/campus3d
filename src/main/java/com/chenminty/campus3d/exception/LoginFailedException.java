package com.chenminty.campus3d.exception;

public class LoginFailedException extends CampusException {
    public LoginFailedException(String message) {
        super(-1002, "Login failed: " + message);
    }
}
