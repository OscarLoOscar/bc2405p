package com.bootcamp.bc_forum.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserPlaceHolder {
  private Integer id;
  private String name;
  private String username;
  private String email;
  private Address address;
  private String phone;
  private String website;
  private Company company;

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  @Setter
  @ToString
  private static class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  @Setter
  @ToString
  private static class Geo {
    private String lat;
    private String lng;
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Getter
  @Setter
  @ToString
  private static class Company {
    private String name;
    private String catchPhrase;
    private String bs;
  }
}
