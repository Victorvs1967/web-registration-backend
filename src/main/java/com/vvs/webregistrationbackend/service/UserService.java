package com.vvs.webregistrationbackend.service;

import com.vvs.webregistrationbackend.dto.UserDTO;

import reactor.core.publisher.Mono;

public interface UserService {
  public Mono<UserDTO> postUserData(UserDTO userDTO);
}
