package com.vvs.webregistrationbackend.service;

import com.vvs.webregistrationbackend.dto.UserDTO;
import com.vvs.webregistrationbackend.model.User;

import reactor.core.publisher.Mono;

public interface UserService {
  public Mono<User> postUserData(UserDTO userDTO);
}
