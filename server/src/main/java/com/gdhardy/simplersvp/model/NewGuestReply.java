package com.gdhardy.simplersvp.model;

/**
 * NewGuestReply
 */

public class NewGuestReply {

  private String firstName;
  private String lastName;
  private String email;
  private Rsvp reply;


  public NewGuestReply(String firstName, String lastName, String email, Rsvp reply) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.reply = reply;
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