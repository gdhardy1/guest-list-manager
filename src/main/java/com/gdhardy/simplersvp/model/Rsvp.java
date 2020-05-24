package com.gdhardy.simplersvp.model;

import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Rsvp
 */
@Schema(name = "RSVP Value", description = "Accepts enumerations 'Going', 'Not Going', or 'No Reply'")
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