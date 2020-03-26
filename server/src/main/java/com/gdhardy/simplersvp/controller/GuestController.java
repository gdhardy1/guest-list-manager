package com.gdhardy.simplersvp.controller;

import java.util.List;

import com.gdhardy.simplersvp.model.Guest;
import com.gdhardy.simplersvp.model.NewGuest;
import com.gdhardy.simplersvp.model.NewGuestReply;
import com.gdhardy.simplersvp.model.Reply;
import com.gdhardy.simplersvp.service.GuestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * GuestController
 */
@RestController
@Tag(name="Guest List Management", description="Endpoints for finding, adding, removing, and updating guests")
public class GuestController { 

  @Autowired
  private GuestService guestService;

  @GetMapping(value="api/guests", produces="application/json")
  @Operation(description="Find all guests.", summary="Find all guests.")
  public List<Guest> findGuests(){
    return guestService.findAllGuests();
  }

  @PostMapping(value="api/guests", consumes="application/json", produces = "application/json")
  @Operation(summary="Add a guest.", description="Add a guest.")
  public Guest add(
    @RequestBody(
      description="Provide first name, last name, email, and optional reply", 
      required=true, 
      content=@Content(
        examples={
          @ExampleObject(name="Without Reply",value="{\"firstName\": \"string\",\"lastName\": \"string\",\"email\": \"string\"}"), 
          @ExampleObject(name="With Reply", value="{\"firstName\": \"string\",\"lastName\": \"string\",\"email\": \"string\",\"reply\": \"GOING\"}")
        }, 
        schema=@Schema(anyOf = {NewGuest.class, NewGuestReply.class})
      )
    ) 
    @org.springframework.web.bind.annotation.RequestBody Guest guest
  ) {
    return guestService.add(guest);
  }

  @GetMapping(value="api/guests/{email}", produces="application/json")
  @Operation(summary="Find a guest by email.", description="Find a guest by email.")
  public Guest findGuest(@Parameter(description = "Email of guest.") @PathVariable(name="email", required=false) String email) {
    return guestService.findGuest(email);
  }

  @PatchMapping(value="api/guests/{email}/rsvp", produces="application/json")
  @Operation(summary="RSVP a guest by email.", description="RSVP a guest by email.")
  public Guest rsvp(
    @Parameter(description = "Email of guest.") @PathVariable String email, 
    @RequestBody @org.springframework.web.bind.annotation.RequestBody Reply reply
  ){
    return guestService.rsvp(email, reply);
  }

  @DeleteMapping(value="api/guests/{email}", produces="application/json")
  @Operation(summary="Remove a guest by email.", description = "Remove a guest by email.")
  public Guest remove(@Parameter(description = "Email of guest.") @PathVariable(name="email") String email){
    return guestService.remove(email);
  }
}