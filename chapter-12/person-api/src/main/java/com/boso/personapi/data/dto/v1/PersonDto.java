package com.boso.personapi.data.dto.v1;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@JsonPropertyOrder({"id", "address", "firstName", "lastName", "gender"})
public class PersonDto implements Serializable {

  private Long id;

  private String firstName;

  private String lastName;

  private String address;

  private String gender;

}
