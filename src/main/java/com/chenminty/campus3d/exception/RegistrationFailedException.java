package com.chenminty.campus3d.exception;

public class RegistrationFailedException extends CampusException {
    public RegistrationFailedException(String msg) {
        super(-1001, "Registration failed: " + msg);
    }
}
