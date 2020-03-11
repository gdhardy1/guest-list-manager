package com.gdhardy.simplersvp.service;

import java.util.List;

import com.gdhardy.simplersvp.model.Guest;
import com.gdhardy.simplersvp.repository.GuestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * GuestService
 */
@Service("guestService")
public class GuestService {

  @Autowired
  private GuestRepository guestRepository;

  public List<Guest> findAllGuests(){

    return guestRepository.findAll();
  }

  public Guest add(Guest guest) {
     
    return guestRepository.save(guest);
  }
}