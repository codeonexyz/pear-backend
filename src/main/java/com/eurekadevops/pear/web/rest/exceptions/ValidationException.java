package com.eurekadevops.pear.web.rest.exceptions;

import org.springframework.validation.BindingResult;

public class ValidationException extends CustomRuntimeException {

    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String key, String value) {
        super(key, value);
    }

    public ValidationException(BindingResult result) {
        super(result);
    }

}
