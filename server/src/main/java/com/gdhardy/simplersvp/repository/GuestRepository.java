package com.gdhardy.simplersvp.repository;

import java.util.List;

import com.gdhardy.simplersvp.model.Guest;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * GuestRepository
 */

@RepositoryRestResource(collectionResourceRel = "guests", path="guests")
public interface GuestRepository extends MongoRepository<Guest, String> {

  List<Guest> findAll();
}