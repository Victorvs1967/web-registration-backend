package com.vvs.webregistrationbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {
  
  private Long id;
  private String email;
  private String password;
  private String firstName;
  private String lastName;
  
  public String getFullName() {
    return firstName != null ? firstName.concat(" ").concat(lastName) : "";
  }
}
