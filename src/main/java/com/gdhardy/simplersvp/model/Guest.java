package com.gdhardy.simplersvp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Guest
 */
@Document(collection = "guests")
@Schema(
  name="Guest", 
  description="Guest entity.", 
  example="{\"id\":\"5e7e159929da9a4a7235c83d\",\"firstName\":\"Dev\",\"lastName\":\"Example\",\"email\":\"devGuest@dev.com\",\"reply\":\"Going\"}"
)
public class Guest {
  @Id private String id;
  private String firstName;
  private String lastName;
  private String email;
  private Rsvp reply;
  
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

  public Rsvp getReply(){
    return reply;
  }
  public void setReply(Rsvp reply){
    this.reply = reply;
  }
}