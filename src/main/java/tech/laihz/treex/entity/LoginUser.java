package tech.laihz.treex.entity;

import lombok.Data;

@Data
public class LoginUser {
  private Integer id;
  private String name;
  private String password;
  private String phone;
  private String email;
}
