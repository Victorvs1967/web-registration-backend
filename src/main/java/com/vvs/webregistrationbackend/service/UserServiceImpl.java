package com.vvs.webregistrationbackend.service;

import java.util.Base64;

import com.vvs.webregistrationbackend.dto.UserDTO;
import com.vvs.webregistrationbackend.model.User;
import com.vvs.webregistrationbackend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public Mono<User> postUserData(UserDTO userDTO) {
    User user = User.builder()
      .email(userDTO.getEmail())
      .password(encryptPassword(userDTO.getPassword()))
      .firstName(userDTO.getFirstName())
      .lastName(userDTO.getLastName())
      .build();

    return userRepository.save(user);
  }
  
  private static String encryptPassword(String password) {
    return Base64.getEncoder().encodeToString(password.getBytes());
  }

  private static String decryptPassword(String password) {
    return new String(Base64.getMimeDecoder().decode(password));
  }
}
