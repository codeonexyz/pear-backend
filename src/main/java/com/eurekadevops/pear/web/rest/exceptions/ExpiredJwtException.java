package com.eurekadevops.pear.web.rest.exceptions;

import org.springframework.validation.BindingResult;

public class ExpiredJwtException extends CustomRuntimeException {

    public ExpiredJwtException() {
    }

    public ExpiredJwtException(String message) {
        super(message);
    }

    public ExpiredJwtException(String key, String value) {
        super(key, value);
    }

    public ExpiredJwtException(BindingResult result) {
        super(result);
    }

}
