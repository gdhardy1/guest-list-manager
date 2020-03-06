package com.gdhardy.simplersvp.controller;

import java.util.List;

import com.gdhardy.simplersvp.model.Guest;
import com.gdhardy.simplersvp.service.GuestService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GuestController
 */
@RestController
public class GuestController {

  private final GuestService guestService;

  GuestController(GuestService guestService){
    this.guestService = guestService;
  }

  @GetMapping(value="api/guests", produces="application/json")
  public List<Guest> findGuests(){
    return guestService.findAllGuests();
  }
}