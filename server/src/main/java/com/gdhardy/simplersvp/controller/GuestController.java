package com.gdhardy.simplersvp.controller;

import java.util.List;

import com.gdhardy.simplersvp.model.Guest;
import com.gdhardy.simplersvp.service.GuestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * GuestController
 */
@RestController
public class GuestController { 

  @Autowired
  private GuestService guestService;

  @GetMapping(value="api/guests", produces="application/json")
  public List<Guest> findGuests(){
    return guestService.findAllGuests();
  }

  @PostMapping(value="api/guests", consumes="application/json", produces = "application/json")
  public Guest add(@RequestBody Guest guest) {
    return guestService.add(guest);
  }
}