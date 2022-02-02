package com.vvs.webregistrationbackend.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import com.vvs.webregistrationbackend.handler.AuthHandler;

@Configuration
public class AuthRouter {
  
  @Bean
  public RouterFunction<ServerResponse> authRoute(AuthHandler authHandle) {
    return RouterFunctions
      .route(POST("/userAuth/register").and(accept(APPLICATION_JSON)), authHandle::register);
  }
}
