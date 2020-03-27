package com.gdhardy.simplersvp.controller;

import java.util.List;

import javax.validation.Valid;

import com.gdhardy.simplersvp.exceptions.ErrorResponse;
import com.gdhardy.simplersvp.model.Guest;
import com.gdhardy.simplersvp.model.NewGuest;
import com.gdhardy.simplersvp.model.Reply;
import com.gdhardy.simplersvp.service.GuestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
  @ApiResponse(
    description = "List of all guests",
    responseCode = "200", 
    content = @Content(array = @ArraySchema(schema=@Schema(implementation = Guest.class)))
  )
  public List<Guest> findGuests(){
    return guestService.findAllGuests();
  }


  @PostMapping(value="api/guests", consumes="application/json", produces = "application/json")
  @Operation(summary="Add a guest.", description="Add a guest.")
  @ApiResponses({
    @ApiResponse(
      description = "Added guest",
      responseCode = "200",
      content = @Content(
        schema = @Schema(implementation = Guest.class)
      )
    ),
    @ApiResponse(
      description = "Bad Request",
      responseCode = "400",
      content = @Content(
        examples = {
          @ExampleObject(value="{\"errors\":[{\"details\":\"email\",\"message\":\"Email must be provided.\"},{\"details\":\"lastName\",\"message\":\"Last name must be provided.\"}]}")
        },
        schema = @Schema(implementation = ErrorResponse.class)
      )
    )
  })
  public Guest add(
    @Valid @RequestBody(
      description="Provide first name, last name, email, and optional reply.", 
      required=true
    ) 
    @org.springframework.web.bind.annotation.RequestBody NewGuest guest
  ) {
    return guestService.add(guest);
  }


  @GetMapping(value="api/guests/{email}", produces="application/json")
  @Operation(summary="Find a guest by email.", description="Find a guest by email.")
  @ApiResponses({
    @ApiResponse(
      description = "Found Guest",
      responseCode = "200"
    ),
    @ApiResponse(
      description = "Guest Not Found",
      responseCode = "404",
      content = @Content(
        examples = {@ExampleObject(
          value="{\"errors\":[{\"message\":\"No guest found for email: hello@email.com\"}]}"
        )},
        schema=@Schema(
          implementation = ErrorResponse.class
        )
      )
    )
  })
  public ResponseEntity<Guest> findGuest(@Parameter(description = "Email of guest.") @PathVariable(name="email", required=true) String email) {
    Guest guest = guestService.findGuest(email);

    if(guest.getId() == null){
      throw new ResourceNotFoundException("No guest found for email: " + email);
    }

    return new ResponseEntity<Guest>(guest, HttpStatus.OK);
  }


  @PatchMapping(value="api/guests/{email}/rsvp", produces="application/json")
  @Operation(summary="RSVP a guest by email.", description="RSVP a guest by email.")
  @ApiResponses({
    @ApiResponse(
      description = "Successful RSVP",
      responseCode = "204"
    ),
    @ApiResponse(
      description = "Guest Not Found",
      responseCode = "404",
      content = @Content(
        examples = {@ExampleObject(
          value="{\"errors\":[{\"message\":\"No guest found for email: hello@email.com\"}]}"
        )},
        schema=@Schema(
          implementation = ErrorResponse.class
        )
      )
    )
  })
  public ResponseEntity<Void> rsvp(
    @Parameter(description = "Email of guest.") @PathVariable String email, 
    @Valid @RequestBody @org.springframework.web.bind.annotation.RequestBody Reply reply
  ){
    Guest guest =  guestService.rsvp(email, reply);

    if(guest.getId() == null){

      throw new ResourceNotFoundException("No guest found for email: " + email);
    }

    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }


  @DeleteMapping(value="api/guests/{email}", produces="application/json")
  @Operation(summary="Remove a guest by email.", description = "Remove a guest by email.")
  @ApiResponses({
    @ApiResponse(
      description = "Guest deleted successfully.",
      responseCode = "204"
    ),
    @ApiResponse(
      description = "Guest Not Found",
      responseCode = "404",
      content = @Content(
        examples = {@ExampleObject(
          value="{\"errors\":[{\"message\":\"No guest found for email: hello@email.com\"}]}"
        )},
        schema=@Schema(
          implementation = ErrorResponse.class
        )
      )
    )
  })
  public ResponseEntity<Void> remove(@Parameter(description = "Email of guest.") @PathVariable(name="email") String email){
    Guest guest = guestService.remove(email);

    if(guest.getId() == null){
      throw new ResourceNotFoundException("Guest not found for email: " + email);
    }

    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }
}