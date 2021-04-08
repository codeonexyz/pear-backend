package com.eurekadevops.pear.web.rest.exceptions;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String key, String value) {
        super(key, value);
    }
}
