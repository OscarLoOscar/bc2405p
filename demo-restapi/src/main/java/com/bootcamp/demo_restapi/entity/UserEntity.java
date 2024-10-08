package com.bootcamp.demo_restapi.entity;

import java.io.Serializable;
import org.springframework.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_users")
public class UserEntity implements Serializable{
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY) 
  private Long id; // BIGINT
  
  private String name;
  private String username;
  
  @Column(name = "useremail")
  private String email;
  private String phone;
  private String website;
  private String dummy;
}
