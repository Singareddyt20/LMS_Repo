package com.springboot.rest.example.exception;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFound(ResourceNotFoundException ex,
            HttpServletRequest request) {
        Error error = new Error();
        error.setMessage(ex.getMessage());
        error.setTimestamp(new Date().getTime());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> badRequest(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Error error = new Error();
        error.setMessage(ex.getMessage());
        error.setTimestamp(new Date().getTime());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, null, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(UpdateBookNotAllowedException.class)
    public ResponseEntity<Object> updateBookNotAllowedException(UpdateBookNotAllowedException ex,
            HttpServletRequest request) {
        Error error = new Error();
        error.setMessage(ex.getMessage());
        error.setTimestamp(new Date().getTime());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, null, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(DeleteNotAllowedException.class)
    public ResponseEntity<Object> deleteNotAllowedException(DeleteNotAllowedException ex,
            HttpServletRequest request) {
        Error error = new Error();
        error.setMessage(ex.getMessage());
        error.setTimestamp(new Date().getTime());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, null, HttpStatus.BAD_REQUEST);
    }
}
