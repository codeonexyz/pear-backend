package com.eurekadevops.pear.web.rest.exceptions;

public class NotFoundException extends CustomRuntimeException {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String key, String value) {
        super(key, value);
    }

}
