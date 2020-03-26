package com.gdhardy.simplersvp.model;

/**
 * Reply
 */
public class Reply {

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