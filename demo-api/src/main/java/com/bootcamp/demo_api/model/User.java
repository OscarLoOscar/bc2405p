package com.bootcamp.demo_api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {
  private int id;
  private String name;
  private String username;
  private String email;
  private Address address;
  private String phone;
  private String website;
  private Company company;

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @ToString
  @Builder
  static class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @ToString
  @Builder
  static class Geo {
    private String lat;
    private String lng;
  }

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @ToString
  @Builder
  static class Company {
    private String name;
    private String catchPhrase;
    private String bs;
  }
}
