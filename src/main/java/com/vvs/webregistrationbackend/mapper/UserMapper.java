package com.vvs.webregistrationbackend.mapper;

import com.vvs.webregistrationbackend.dto.UserDTO;
import com.vvs.webregistrationbackend.model.User;

public interface UserMapper {
  public UserDTO toDTO(User user);
  public User fromDTO(UserDTO userDTO);
}
