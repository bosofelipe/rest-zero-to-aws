package com.boso.personapi.model;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Person implements Serializable {

  private Long id;
  private String firstName;
  private String lastName;
  private String address;
  private String gender;

}
