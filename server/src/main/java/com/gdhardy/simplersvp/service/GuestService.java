package com.gdhardy.simplersvp.service;

import java.util.ArrayList;
import java.util.List;

import com.gdhardy.simplersvp.model.Guest;

import org.springframework.stereotype.Service;

/**
 * GuestService
 */
@Service
public class GuestService {

  public List<Guest> findAllGuests(){
    ArrayList<Guest> guests = new ArrayList<Guest>();
    Guest guest = new Guest();
    guest.setFirstName("James");
    guests.add(guest);
    return guests;
  }
}