package com.gdhardy.simplersvp.model;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Reply
 */
@Schema(
  name = "Reply", 
  description = "For sending enumerated RSVP value.", 
  example = "{\"reply\":\"Not Going\"}"
)
public class Reply {

  @NotNull(message="Must reply 'Going', 'Not Going', or 'No Reply'")
  private Rsvp reply;

  public Reply() {
  }

  public Reply(Rsvp reply) {
    this.reply = reply;
  }

  public Rsvp getReply() {
    return this.reply;
  }

  public void setReply(Rsvp reply) {
    this.reply = reply;
  }

  public Reply reply(Rsvp reply) {
    this.reply = reply;
    return this;
  }
}