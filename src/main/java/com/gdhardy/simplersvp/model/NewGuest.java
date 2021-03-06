package com.gdhardy.simplersvp.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * NewGuest
 */
@Schema(
  name = "New Guest", 
  description = "For creating a new guest.",
  example = "{\"firstName\":\"Dev\",\"lastName\":\"Example\",\"email\":\"devGuest@dev.com\",\"reply\":\"Going\"}"
)
public class NewGuest {

  @NotNull(message="First name must be provided.")
  @NotBlank(message = "First name must not be blank.")
  private String firstName;
  
  @NotNull(message="Last name must be provided.")
  @NotBlank(message = "Last name must not be blank.")
  private String lastName;
  
  @NotNull(message="Email must be provided.")
  @NotBlank(message = "Email must not be blank.")
  private String email;
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