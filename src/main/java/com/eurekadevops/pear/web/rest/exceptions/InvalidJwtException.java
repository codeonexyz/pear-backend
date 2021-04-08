package com.eurekadevops.pear.web.rest.exceptions;

import org.springframework.validation.BindingResult;

public class InvalidJwtException extends CustomRuntimeException {

    public InvalidJwtException() {
    }

    public InvalidJwtException(String message) {
        super(message);
    }

    public InvalidJwtException(String key, String value) {
        super(key, value);
    }

    public InvalidJwtException(BindingResult result) {
        super(result);
    }

}
