package com.eurekadevops.pear.web.rest.exceptions;

public class UniqueEmailException extends CustomRuntimeException {

    public UniqueEmailException() {
    }

    public UniqueEmailException(String message) {
        super(message);
    }

    public UniqueEmailException(String key, String value) {
        super(key, value);
    }

}
