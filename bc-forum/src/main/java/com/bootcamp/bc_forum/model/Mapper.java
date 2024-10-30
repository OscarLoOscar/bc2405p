package com.bootcamp.bc_forum.model;

import org.springframework.stereotype.Component;
import com.bootcamp.bc_forum.entity.AddressEntity;
import com.bootcamp.bc_forum.entity.CommentEntity;
import com.bootcamp.bc_forum.entity.CompanyEntity;
import com.bootcamp.bc_forum.entity.GeoEntity;
import com.bootcamp.bc_forum.entity.PostEntity;
import com.bootcamp.bc_forum.entity.UserEntity;

@Component
public class Mapper {

  public UserEntity map(UserPlaceHolder userPlaceHolder) {
    return UserEntity.builder()//
        .name(userPlaceHolder.getName())//
        .username(userPlaceHolder.getUsername())//
        .phone(userPlaceHolder.getPhone())//
        .email(userPlaceHolder.getEmail())//
        .website(userPlaceHolder.getWebsite())//
        .build();
  }

  public AddressEntity mapToAddressEntity(UserPlaceHolder userPlaceHolder) {
    return AddressEntity.builder()//
        .street(userPlaceHolder.getAddress().getStreet())//
        .suite(userPlaceHolder.getAddress().getSuite())//
        .city(userPlaceHolder.getAddress().getCity())//
        .zipcode(userPlaceHolder.getAddress().getZipcode())//
        .build();

  }

  public CompanyEntity mapToCompanyEntity(UserPlaceHolder userPlaceHolder) {
    return CompanyEntity.builder()//
        .name(userPlaceHolder.getCompany().getName())//
        .catchPhrase(userPlaceHolder.getCompany().getCatchPhrase())//
        .bs(userPlaceHolder.getCompany().getBs())//
        .build();

  }

  public GeoEntity mapToGeoEntity(UserPlaceHolder userPlaceHolder) {
    return GeoEntity.builder()//
        .lat(userPlaceHolder.getAddress().getGeo().getLat())//
        .lng(userPlaceHolder.getAddress().getGeo().getLng())//
        .build();

  }

  public PostEntity map(PostPlaceHolder postPlaceHolder) {
    return PostEntity.builder()//
        .title(postPlaceHolder.getTitle())//
        .body(postPlaceHolder.getBody())//
        .build();

  }

  public CommentEntity map(CommentPlaceHolder commentPlaceHolder) {
    return CommentEntity.builder()//
        .name(commentPlaceHolder.getName())//
        .email(commentPlaceHolder.getEmail())//
        .body(commentPlaceHolder.getBody())//
        .build();

  }
}
