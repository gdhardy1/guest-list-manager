package com.gdhardy.simplersvp.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * ErrorDetails
 */
@JsonInclude(Include.NON_NULL)
public class ErrorDetails{
  private String details;
  private String message;

  public ErrorDetails() {
  }

  public ErrorDetails(String details, String message) {
    this.details = details;
    this.message = message;
  }

  public ErrorDetails(String message){
    this.message = message;
  }

  public String getDetails() {
    return this.details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
  
}