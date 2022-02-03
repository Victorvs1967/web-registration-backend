package com.vvs.webregistrationbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.WebFilterChainServerAuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import reactor.core.publisher.Mono;

@Configuration
public class SecurityConfig {
  
  @Bean
  public SecurityWebFilterChain chain(ServerHttpSecurity http) {
    return http
        // add AuthenticationWebFilter and set the handler
        .csrf().disable()
        .cors().configurationSource(createCorsConfiguration())
        .and()
        .exceptionHandling()
        .authenticationEntryPoint((swe, e) -> Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED)))
        .accessDeniedHandler((swe, e) -> Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN)))
        .and()
        .formLogin()
        .authenticationSuccessHandler(new WebFilterChainServerAuthenticationSuccessHandler())
        .authenticationFailureHandler((webFilterExchange, exception) -> Mono.error(exception))
        .and()
        // allow all path accessed by all role
        .authorizeExchange()
        .pathMatchers("/**").permitAll()
        .and()
        .httpBasic().disable()
        .build();
  }

  public CorsConfigurationSource createCorsConfiguration() {

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();

    config.setAllowCredentials(true);
    config.addAllowedOrigin("http://localhost:4200");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");

    source.registerCorsConfiguration("/**", config);

    return source;
  }

}
