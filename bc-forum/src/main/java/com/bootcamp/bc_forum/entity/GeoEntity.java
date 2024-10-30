package com.bootcamp.bc_forum.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "t_geo")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class GeoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "geo_id")
  private Long id;

  private String lat;
  private String lng;

  @OneToOne
  @JoinColumn(name = "address_id")
  private AddressEntity address;

}