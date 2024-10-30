package com.bootcamp.bc_forum.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "t_address")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class AddressEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "address_id")
  private Long id;

  private String street;
  private String suite;
  private String city;
  private String zipcode;

  @OneToOne(mappedBy = "address", //
      cascade = {CascadeType.ALL}, //
      fetch = FetchType.LAZY)
  private GeoEntity geoEntity;

  @OneToOne
  @JoinColumn(name  = "user_id")
  private UserEntity user;

}

