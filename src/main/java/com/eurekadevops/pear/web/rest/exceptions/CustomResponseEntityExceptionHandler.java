package com.eurekadevops.pear.web.rest.exceptions;

import com.eurekadevops.pear.exceptions.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException exc, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse();
        
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(exc.getMessage());
        response.setTimestamp(System.currentTimeMillis());
        response.setDetails(request.getDescription(false));
        
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler
    public final ResponseEntity<ExceptionResponse> handleAuthenticationException(AuthenticationException exc, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse();
        
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exc.getMessage());
        response.setDetails(request.getDescription(false));
        response.setTimestamp(System.currentTimeMillis());
        response.setErrors(exc.getErrors());
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler
    public final ResponseEntity<ExceptionResponse> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException exc, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse();
        
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exc.getMessage());
        response.setDetails(request.getDescription(false));
        response.setTimestamp(System.currentTimeMillis());
        response.addError("badCredentials", "Incorrect username or password");
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler
    public final ResponseEntity<ExceptionResponse> handleUniqueEmailException(UniqueEmailException exc, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse();
        
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exc.getMessage());
        response.setDetails(request.getDescription(false));
        response.setTimestamp(System.currentTimeMillis());
        response.setErrors(exc.getErrors());
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler
    public final ResponseEntity<ExceptionResponse> handleValidationException(ValidationException exc, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse();
        
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exc.getMessage());
        response.setDetails(request.getDescription(false));
        response.setTimestamp(System.currentTimeMillis());
        response.setErrors(exc.getErrors());
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler
    public final ResponseEntity<ExceptionResponse> handleBadRequestAlertException(BadRequestAlertException exc, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse();
        
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exc.getMessage());
        response.setDetails(request.getDescription(false));
        response.setTimestamp(System.currentTimeMillis());
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
