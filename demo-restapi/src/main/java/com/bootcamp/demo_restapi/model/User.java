package com.bootcamp.demo_restapi.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class User {
  private Integer id;
  private String name;
  private String username;
  private String email;
  private Address address;
  private String phone;
  private String website;
  private Company company;

  @Getter
  @ToString
  public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    @Getter
    @ToString
    public class Geo {
      private String lat;
      private String lng;
    }
  }

  @Getter
  @ToString
  public class Company {
    private String name;
    private String catchPhrase;
    private String bs;
  }
}
