package com.boso.personapi.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import java.io.Serializable;
import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode
@Getter
@Setter
@JsonPropertyOrder({"id", "firstName", "lastName", "address", "gender", "enabled"})
public class PersonDto extends RepresentationModel implements Serializable {

  @Mapping("id")
  @JsonProperty("id")
  private Long key;

  private String firstName;

  private String lastName;

  private String address;

  private String gender;

  private Date birthDate;

  private Boolean enabled;

}
