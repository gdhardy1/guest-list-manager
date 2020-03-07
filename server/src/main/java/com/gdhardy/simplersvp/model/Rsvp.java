package com.gdhardy.simplersvp.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Rsvp
 */
public enum Rsvp {
  GOING("Going"), NOT_GOING("Not Going"), NO_REPLY("No Reply");

  private String value;

  Rsvp(String value){
    this.value = value;
  }

  @JsonValue
  public String getReply(){
    return value;
  }

};