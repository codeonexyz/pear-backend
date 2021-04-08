package com.eurekadevops.pear.web.rest.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class NotEnoughQuantityException extends RuntimeException {
    
    private Integer requestedQuantity;
    private Integer availableQuantity;

    public NotEnoughQuantityException() {
    }

    public NotEnoughQuantityException(String message) {
        super(message);
    }

    public NotEnoughQuantityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughQuantityException(Integer requestedQuantity, Integer availableQuantity, String message) {
        super(message);
        this.requestedQuantity = requestedQuantity;
        this.availableQuantity = availableQuantity;
    }

}
