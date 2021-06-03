package com.boso.personapi.data.dto;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
public class PersonDto implements Serializable {

  private Long id;

  private String firstName;

  private String lastName;

  private String address;

  private String gender;

}
