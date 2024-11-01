package com.bootcamp.bc_forum.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true) // ignore unknown properties during JSON deserialization.
public class UserDTO {
  private Long id;

  private String name;
  @Column(name = "user_name")
  private String username;
  private String email;
  private String phone;
  private String website;
  private AddressDTO address;
  private CompanyDTO company;
  private List<PostDTO> posts = new ArrayList<>();

  @AllArgsConstructor
  @NoArgsConstructor
  @Setter
  @Getter
  @ToString
  @Builder
  @JsonIgnoreProperties
  public static class PostDTO {
    private Long id;
    private String title;
    private String body;
    private List<CommentDTO> comments = new ArrayList<>();
  } 

  @AllArgsConstructor
  @NoArgsConstructor
  @Setter
  @Getter
  @ToString
  @Builder
  @JsonIgnoreProperties
  public static class AddressDTO {
    private Long id;

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoDTO geoDTO;
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Setter
  @Getter
  @ToString
  @Builder
  @JsonIgnoreProperties
  public static class CompanyDTO {
    private Long id;
    private String name;
    private String catchPhrase;
    private String bs;

  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Setter
  @Getter
  @ToString
  @Builder
  @JsonIgnoreProperties
  public static class GeoDTO {
    private Long id;
    private String lat;
    private String lng;
  }
}
