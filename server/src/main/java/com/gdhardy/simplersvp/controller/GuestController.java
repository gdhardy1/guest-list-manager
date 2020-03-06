package com.gdhardy.simplersvp.controller;

import java.util.ArrayList;
import java.util.List;

import com.gdhardy.simplersvp.model.Guest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GuestController
 */
@RestController
public class GuestController {

  @GetMapping(value="api/guests", produces="application/json")
  public List<Guest> findGuests(){
    ArrayList<Guest> guests = new ArrayList<Guest>();
    Guest guest = new Guest();
    guest.setFirstName("James");
    guests.add(guest);
    return guests;
  }
}