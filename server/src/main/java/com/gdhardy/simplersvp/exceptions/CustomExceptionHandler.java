package com.gdhardy.simplersvp.exceptions;

import java.util.ArrayList;
import java.util.List;

import com.gdhardy.simplersvp.controller.GuestController;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * CustomExceptionHandler
 */
@ControllerAdvice(assignableTypes = GuestController.class)
public class CustomExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponse handleException(MethodArgumentNotValidException ex) {

    List<FieldError> errors = ex.getBindingResult().getFieldErrors();

    List<ErrorDetails> errorDetails = new ArrayList<>();
    for (FieldError fieldError : errors) {
        ErrorDetails error = new ErrorDetails();
        error.setFieldName(fieldError.getField());
        error.setMessage(fieldError.getDefaultMessage());
        errorDetails.add(error);
    }

    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setErrors(errorDetails);

    return errorResponse;
  }
}