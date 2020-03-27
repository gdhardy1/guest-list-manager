package com.gdhardy.simplersvp.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * ErrorResponse
 */

public class ErrorResponse {

  private List<ErrorDetails> errors;

  public ErrorResponse() {
    this.errors = new ArrayList<ErrorDetails>();
  }

  public ErrorResponse(List<ErrorDetails> errors) {
    this.errors = errors;
  }

  public List<ErrorDetails> getErrors() {
    return this.errors;
  }

  public void setErrors(List<ErrorDetails> errors) {
    this.errors = errors;
  }

  public void addError(ErrorDetails error){
    this.errors.add(error);
  }

}