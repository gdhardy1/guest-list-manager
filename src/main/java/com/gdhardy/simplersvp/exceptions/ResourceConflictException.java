package com.gdhardy.simplersvp.exceptions;

/**
 * ResourceConflictException
 */
public class ResourceConflictException extends RuntimeException{

  private static final long serialVersionUID = -4969073238944490571L;

  public ResourceConflictException(String message) {
      super(message);
  }

}