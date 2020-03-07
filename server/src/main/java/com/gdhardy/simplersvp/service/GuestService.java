package com.gdhardy.simplersvp.service;

import java.util.ArrayList;
import java.util.List;

import com.gdhardy.simplersvp.model.Guest;

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
}