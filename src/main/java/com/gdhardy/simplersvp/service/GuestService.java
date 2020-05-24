package com.gdhardy.simplersvp.service;

import java.util.List;

import com.gdhardy.simplersvp.model.Guest;
import com.gdhardy.simplersvp.model.NewGuest;

import com.gdhardy.simplersvp.model.Reply;
import com.gdhardy.simplersvp.model.Rsvp;
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

  public Guest add(NewGuest newGuest) {

    if(guestRepository.findByEmail(newGuest.getEmail()) != null){
      return new Guest();
    }

    if (newGuest.getReply() == null){
      newGuest.setReply(Rsvp.valueOf("NO_REPLY"));
    }

    Guest guest = new Guest();
    guest.setFirstName(newGuest.getFirstName());
    guest.setLastName(newGuest.getLastName());
    guest.setEmail(newGuest.getEmail());
    guest.setReply(newGuest.getReply());

    return guestRepository.save(guest);
  }


  public Guest findGuest(String email) {
    Guest guest = guestRepository.findByEmail(email);
    if(guest == null){
      // return null entity
      return new Guest();
    }

    return guest;
  }

  public Guest addGuest(Guest guest){
    return guestRepository.save(guest);
  }

  public Guest remove(String email){
    Guest guest = guestRepository.findByEmail(email);

    if(guest == null){
      return new Guest();
    }
    
    guestRepository.delete(guest);

    return guest;
  }

  public Guest rsvp(String email, Reply reply){
    Guest guest = guestRepository.findByEmail(email);

    if(guest == null){
      return new Guest();
    }
    
    guest.setReply(reply.getReply());
    return guestRepository.save(guest);
  }

}