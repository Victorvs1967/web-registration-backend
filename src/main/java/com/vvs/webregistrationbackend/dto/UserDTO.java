package com.vvs.webregistrationbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
  
  private Long id;
  private String email;
  private String password;
  private String firstName;
  private String lastName;
  
  private String fullName = (firstName != null) ? firstName.concat(" ").concat(lastName) : "";
}
