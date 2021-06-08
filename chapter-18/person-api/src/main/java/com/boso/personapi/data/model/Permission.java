package com.boso.personapi.data.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Entity
@Table(name = "permission")
public class Permission implements GrantedAuthority, Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "description")
  private String description;

  @Override
  public String getAuthority() {
    return this.description;
  }
}
