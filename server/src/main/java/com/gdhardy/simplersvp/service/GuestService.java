package com.gdhardy.simplersvp.service;

import java.util.List;

import com.gdhardy.simplersvp.model.Guest;
import com.gdhardy.simplersvp.model.Reply;
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


  public Guest findGuest(String email) {
    return guestRepository.findByEmail(email);
  }

  public Guest addGuest(Guest guest){
    return guestRepository.save(guest);
  }

  public Guest remove(String email){
    Guest guest = guestRepository.findByEmail(email);
    guestRepository.delete(guest);

    return guest;
  }

  public Guest rsvp(String email, Reply reply){
    Guest guest = guestRepository.findByEmail(email);
    guest.setReply(reply.getReply());
    return guestRepository.save(guest);
  }

}