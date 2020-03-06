package com.gdhardy.simplersvp.model;

import org.springframework.data.annotation.Id;

/**
 * Guest
 */
public class Guest {
  @Id private String id;
  private String firstName;
  private String lastName;
  private String email;
  private Enum<Rsvp> reply;
  
  public String getId(){
    return id;
  }

  public void setId(String id){
    this.id = id;
  }
  
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Enum<Rsvp> getReply(){
    return reply;
  }
  public void setReply(Enum<Rsvp> reply){
    this.reply = reply;
  }
}