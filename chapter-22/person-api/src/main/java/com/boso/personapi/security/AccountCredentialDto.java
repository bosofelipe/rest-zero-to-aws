package com.boso.personapi.security;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class AccountCredentialDto implements Serializable {

  private String username;
  private String password;

}
