package com.vvs.webregistrationbackend.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import com.vvs.webregistrationbackend.dto.UserDTO;
import com.vvs.webregistrationbackend.service.UserService;

@Component
public class AuthHandler {

  @Autowired
  private UserService userService;
  
  public Mono<ServerResponse> register(ServerRequest request) {
    Mono<UserDTO> user = request
      .bodyToMono(UserDTO.class)
      .flatMap(userService::postUserData)
      .switchIfEmpty(Mono.empty());

    return ServerResponse
      .ok()
      .contentType(APPLICATION_JSON)
      .body(user, UserDTO.class);
  }
}
