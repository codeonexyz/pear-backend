package com.eurekadevops.pear.web.rest.exceptions;

import java.util.HashMap;
import java.util.Map;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class CustomRuntimeException extends RuntimeException {
    
    private Map<String, String> errors = new HashMap<>();

    public CustomRuntimeException() {
    }

    public CustomRuntimeException(String message) {
        super(message);
    }
    
    public CustomRuntimeException(String key, String value) {
        this.errors.put(key, value);
    }
    
    public CustomRuntimeException(BindingResult result) {
        for(FieldError error : result.getFieldErrors()) {
            this.errors.put(error.getField(), error.getDefaultMessage());
        }
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

}
