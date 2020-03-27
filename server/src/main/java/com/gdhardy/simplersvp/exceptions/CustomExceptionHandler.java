package com.gdhardy.simplersvp.exceptions;

import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * CustomExceptionHandler
 */
@RestControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException ex) {

    List<FieldError> errors = ex.getBindingResult().getFieldErrors();
    ErrorResponse errorResponse = new ErrorResponse();

    for (FieldError fieldError : errors) {
      ErrorDetails error = new ErrorDetails(
        fieldError.getField() + " field error.",
        fieldError.getDefaultMessage()
      );
      errorResponse.addError(error);
    }

    return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> handle(ResourceNotFoundException ex){
    ErrorResponse errorResponse = new ErrorResponse();
    ErrorDetails error = new ErrorDetails(ex.getMessage());
    errorResponse.addError(error);

    return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
  }
}