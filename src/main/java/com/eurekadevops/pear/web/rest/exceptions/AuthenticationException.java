package com.eurekadevops.pear.web.rest.exceptions;

public class AuthenticationException extends CustomRuntimeException {

    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String key, String value) {
        super(key, value);
    }

}
