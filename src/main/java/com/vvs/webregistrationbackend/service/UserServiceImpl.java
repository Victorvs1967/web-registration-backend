package com.vvs.webregistrationbackend.service;

import java.util.Base64;

import com.vvs.webregistrationbackend.dto.UserDTO;
import com.vvs.webregistrationbackend.exception.GlobalException;
import com.vvs.webregistrationbackend.mapper.UserMapper;
import com.vvs.webregistrationbackend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserMapper userMapper;

  @Override
  public Mono<UserDTO> postUserData(UserDTO userDTO) {
    return isUserExist(userDTO.getEmail())
      .filter(userExist -> !userExist)
      .switchIfEmpty(Mono.error(new GlobalException(HttpStatus.BAD_REQUEST, "User already exist.")))
      .map(aBoolean -> userDTO)
      .map(userMapper::fromDTO)
      .flatMap(userRepository::save)
      .map(userMapper::toDTO);
  }

    private static String decryptPassword(String password) {
    return new String(Base64.getMimeDecoder().decode(password));
  }

  private Mono<Boolean> isUserExist(String email) {
    return userRepository.findByEmail(email)
      .map(user -> true)
      .switchIfEmpty(Mono.just(false));
  }

}
