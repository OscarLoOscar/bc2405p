package com.bootcamp.bc_forum.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "t_users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;

    @Column(name = "user_name")
    private String username;

    private String email;

    private String phone;
    private String website;

    @OneToOne(mappedBy = "user", //
            cascade = {CascadeType.PERSIST, //
                    CascadeType.MERGE}, //
            fetch = FetchType.LAZY)
    private AddressEntity addressEntity;

    @OneToOne(mappedBy = "user", //
            cascade = {CascadeType.PERSIST, //
                    CascadeType.MERGE}, //
            fetch = FetchType.LAZY)
    private CompanyEntity companyEntity;

}
