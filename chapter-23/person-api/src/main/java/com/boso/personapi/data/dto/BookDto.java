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
@JsonPropertyOrder({"id", "author", "launchDate", "price", "title"})
public class BookDto extends RepresentationModel implements Serializable {

  @Mapping("id")
  @JsonProperty("id")
  private Long key;
  private String author;
  private Date launchDate;
  private Double price;
  private String title;

}
