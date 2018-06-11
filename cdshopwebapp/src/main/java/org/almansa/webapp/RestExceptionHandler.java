package org.almansa.webapp;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleExceptiion(HttpRequest request, Exception ex){
        System.out.println("------------------------");
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseBody
    public ResponseEntity<Object> handleEmptyResultDataAccessException(HttpRequest request, EmptyResultDataAccessException ex){
        System.out.println("------------------------");
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }    
}
