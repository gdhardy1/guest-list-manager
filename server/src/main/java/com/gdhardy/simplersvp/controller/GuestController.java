package com.gdhardy.simplersvp.controller;

import java.util.List;

import com.gdhardy.simplersvp.model.Guest;
import com.gdhardy.simplersvp.service.GuestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
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
  public Guest add(@RequestBody Guest guest) {
    return guestService.add(guest);
  }
}