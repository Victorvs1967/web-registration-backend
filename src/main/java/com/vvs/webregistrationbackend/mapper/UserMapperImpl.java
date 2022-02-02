package com.vvs.webregistrationbackend.mapper;

import java.util.Base64;

import com.vvs.webregistrationbackend.dto.UserDTO;
import com.vvs.webregistrationbackend.model.User;

import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

  @Override
  public UserDTO toDTO(User user) {
    return UserDTO.builder()
        .id(user.getId())
        .email(user.getEmail())
        .password(user.getPassword())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .build();
  }

  @Override
  public User fromDTO(UserDTO userDTO) {
    return User.builder()
    .email(userDTO.getEmail())
    .password(encryptPassword(userDTO.getPassword()))
    .firstName(userDTO.getFirstName())
    .lastName(userDTO.getLastName())
    .build();
  }

  private static String encryptPassword(String password) {
    return Base64.getEncoder().encodeToString(password.getBytes());
  }

}
