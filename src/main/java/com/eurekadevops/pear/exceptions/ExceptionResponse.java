package com.eurekadevops.pear.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    
    private int status;
    private String message;
    private long timestamp;
    private String details;
    private Map<String, String> errors = new HashMap<>();

    public void addError(String key, String msg) {
        this.errors.put(key, msg);
    }

}
