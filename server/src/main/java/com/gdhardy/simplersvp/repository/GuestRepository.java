package com.gdhardy.simplersvp.repository;

import java.util.List;

import com.gdhardy.simplersvp.model.Guest;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * GuestRepository
 */

@RepositoryRestResource(collectionResourceRel = "guests", path="guests")
public interface GuestRepository extends MongoRepository<Guest, String> {

  List<Guest> findAll();
  Guest findByEmail(@Param("email") String email);
  <S extends Guest> S save(S guest);
  void deleteById(String id);
  <T extends Guest> void delete(T entity);
}