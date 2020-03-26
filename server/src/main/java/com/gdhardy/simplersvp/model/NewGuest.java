package com.gdhardy.simplersvp.model;

import javax.validation.constraints.NotNull;

/**
 * NewGuest
 */
public class NewGuest {

  @NotNull(message="First name must be provided.") private String firstName;
  @NotNull(message="Last name must be provided.") private String lastName;
  @NotNull(message="Email must be provided.") private String email;
  private Rsvp reply;


  public NewGuest(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Rsvp getReply() {
    return this.reply;
  }

  public void setReply(Rsvp reply) {
    this.reply = reply;
  }
}