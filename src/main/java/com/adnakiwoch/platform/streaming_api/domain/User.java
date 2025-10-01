package com.adnakiwoch.platform.streaming_api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Platform Users")
public class User {
  @Id private long id;

  private String name;

  private String email;
}
