package com.vvs.webregistrationbackend.repository;

import com.vvs.webregistrationbackend.model.User;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveSortingRepository<User, Long> {
  
}
