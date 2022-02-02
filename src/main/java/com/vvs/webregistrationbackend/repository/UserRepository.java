package com.vvs.webregistrationbackend.repository;

import com.vvs.webregistrationbackend.model.User;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveSortingRepository<User, Long> {

  Mono<User> findByEmail(String email);
  
}
